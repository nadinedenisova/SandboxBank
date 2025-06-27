package com.example.sandboxbank.App.core.deposit.data.network

import com.example.sandboxbank.Api
import com.example.sandboxbank.App.core.deposit.data.UserFinancialStats
import com.example.sandboxbank.App.core.deposit.domain.db.FinancialItemRepository
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import com.example.sandboxbank.CreateCreditRequest
import com.example.sandboxbank.CreateDepositRequest
import com.example.sandboxbank.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response


/**
 * поймать бы человека, который в API не прописал методы для получения конкретного продукта или фильтрафии
 * приходится при получении кредитов, депозитов или конкретного продукта получать весь список и фильтровать на клиенте
 * Классический антипаттерн
 */
class FinancialItemNetworkRepositoryImpl(
    private val api: Api,
    private val accessTokenProvider: suspend () -> String
): FinancialItemRepository {

    override suspend fun getUserFinancialStats(userId: Long): Result<UserFinancialStats> = withContext(Dispatchers.IO) {
        try {
            // Получаем все продукты
            val result = getAllProducts(userId)

            when (result.isSuccess) {
                true -> {
                    val allItems = result.getOrNull() ?: emptyList()

                    // Фильтруем по типу
                    val deposits = allItems.filter { it.type == "product_deposit" }
                    val credits = allItems.filter { it.type == "product_credit" }

                    // Считаем статистику
                    val depositCount = deposits.size
                    val depositTotal = deposits.sumOf { it.balance }

                    val creditCount = credits.size
                    val creditTotal = credits.sumOf { it.balance }

                    // Возвращаем результат
                    Result.success(
                        UserFinancialStats(
                            depositCount = depositCount,
                            depositTotal = depositTotal,
                            creditCount = creditCount,
                            creditTotal = creditTotal
                        )
                    )
                }
                false -> {
                    Result.failure(result.exceptionOrNull() ?: Exception("Failed to load products"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insert(
        userId: Long,
        financialItem: FinancialItem,
        requestNumber: Long
    ): Result<FinancialItem> = withContext(Dispatchers.IO) {
        try {
            val token = accessTokenProvider()

            val response: Response<out ProductResponse> = when (financialItem) {
                is Credit -> {
                    val request = CreateCreditRequest(
                        userId = userId,
                        currentCreditNumber = financialItem.id,
                        requestNumber = requestNumber,
                        balance = financialItem.balance,
                        period = financialItem.period,
                    )
                    api.createCredit(request, "Bearer $token") as Response<out ProductResponse>
                }

                is Deposit -> {
                    val request = CreateDepositRequest(
                        userId = userId,
                        currentDepositNumber = financialItem.id,
                        requestNumber = requestNumber,
                        percentType = financialItem.percentType,
                        period = financialItem.period,
                    )
                    api.createDeposit(request, "Bearer $token") as Response<out ProductResponse>
                }
            }

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body.product.toLocalModel())
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(HttpException(response))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    //похоже не нужен, делается через transaction
    override suspend fun changeBalance(id: Int, delta: Long) {
        TODO()
    }

    override suspend fun getAllCredits(userId: Long): Result<List<FinancialItem>> = withContext(Dispatchers.IO) {
        try {
            val result = getAllProducts(userId)
            when (result.isSuccess) {
                true -> {
                    val allItems = result.getOrNull() ?: emptyList()
                    val creditItems = allItems.filterByType("product_credit")
                    Result.success(creditItems)
                }
                false -> {
                    Result.failure(result.exceptionOrNull() ?: Exception("Failed to load products"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllDeposits(userId: Long): Result<List<FinancialItem>> = withContext(Dispatchers.IO) {
        try {
            val result = getAllProducts(userId)
            when (result.isSuccess) {
                true -> {
                    val allItems = result.getOrNull() ?: emptyList()
                    val creditItems = allItems.filterByType("product_deposit")
                    Result.success(creditItems)
                }
                false -> {
                    Result.failure(result.exceptionOrNull() ?: Exception("Failed to load products"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getById(id: Long, userId: Long): Result<FinancialItem> = withContext(Dispatchers.IO) {
        try {
            // Получаем все продукты
            val result = getAllProducts(userId)

            // Обрабатываем результат
            when (result.isSuccess) {
                true -> {
                    val items = result.getOrNull()
                    val item = items?.find { it.id == id }

                    if (item != null) {
                        Result.success(item)
                    } else {
                        Result.failure(Exception("Product with ID $id not found"))
                    }
                }
                false -> {
                    Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllProducts(userId: Long): Result<List<FinancialItem>> = withContext(Dispatchers.IO) {
        try {
            val token = accessTokenProvider()
            val response = api.getProducts(userId, "Bearer $token")
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val items = response.body()?.products?.map { it.toLocalModel() } ?: emptyList()
                    Result.success(items)
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(HttpException(response))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun List<FinancialItem>.filterByType(type: String): List<FinancialItem> =
        this.filter { it.type == type }
}
