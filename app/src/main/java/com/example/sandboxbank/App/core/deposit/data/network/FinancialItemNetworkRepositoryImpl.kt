package com.example.sandboxbank.App.core.deposit.data.network

import com.example.sandboxbank.Api
import com.example.sandboxbank.App.core.deposit.data.FinancialType
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
import java.math.BigDecimal
import kotlin.random.Random


/**
 * поймать бы человека, который в API не прописал методы для получения конкретного продукта или фильтрафии
 * приходится при получении кредитов, депозитов или конкретного продукта получать весь список и фильтровать на клиенте
 * Классический антипаттерн
 */
class FinancialItemNetworkRepositoryImpl(
    private val api: Api,
    // private val accessTokenProvider: suspend () -> String
): FinancialItemRepository {

    override suspend fun getUserFinancialStats(userId: Long): Result<UserFinancialStats> = withContext(Dispatchers.IO) {
        try {
            // Получаем все продукты
            val result = getAllProducts(userId)

            when (result.isSuccess) {
                true -> {
                    val allItems = result.getOrNull() ?: emptyList()

                    // Фильтруем по типу
                    val deposits = allItems.filterByType(FinancialType.DEPOSIT)
                    val credits = allItems.filterByType(FinancialType.CREDIT)

                    // Считаем статистику
                    val depositCount = deposits.size
                    val depositTotal = deposits.sumOf { it.balance }

                    val creditCount = credits.size
                    val creditTotal = credits.sumOf { it.balance }

                    // Возвращаем результатP
                    Result.success(
                        UserFinancialStats(
                            depositCount = depositCount,
                            depositTotal = depositTotal.toLong(),
                            creditCount = creditCount,
                            creditTotal = creditTotal.toLong()
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
            //val token = accessTokenProvider()
            val token = "test token"

            val response: Response<out ProductResponse> = when (financialItem) {
                is Credit -> {
                    val request = CreateCreditRequest(
                        userId = userId,
                        currentCreditNumber = financialItem.id,
                        requestNumber = requestNumber,
                        balance = financialItem.balance.toLong(),
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
                    val creditItems = allItems.filterByType(FinancialType.CREDIT)
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
                    val creditItems = allItems.filterByType(FinancialType.DEPOSIT)
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
            val token = "test token"
            //val token = accessTokenProvider()
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
                // 70% шанс на mock, иначе ошибка
                if (Random.nextInt(100) < 70) {
                    Result.success(generateMockData())
                } else {
                    Result.failure(HttpException(response))
                }
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun List<FinancialItem>.filterByType(type: FinancialType): List<FinancialItem> =
        this.filter { it.type == type }
}

// Функция для создания фиктивных данных
private fun generateMockData(): List<FinancialItem> {
    return listOf(
        Credit(
            id = 1,
            type = FinancialType.CREDIT,
            openDate = System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 15,
            percentRate = 49.5,
            percentType = 2,
            period = 12,
            balance = BigDecimal(1234567L),
            name = "Кредит №1"
        ),
        Deposit(
            id = 2,
            type = FinancialType.DEPOSIT,
            openDate = System.currentTimeMillis() - 2000 * 60 * 60 * 24 * 15,
            percentRate = 8.5,
            percentType = 2,
            period = 12,
            balance = BigDecimal(1234567L),
            name = "Вклад №1"
        ),
        Credit(
            id = 3,
            type = FinancialType.CREDIT,
            openDate = System.currentTimeMillis() - 3000 * 60 * 60 * 24 * 15,
            percentRate = 249.5,
            percentType = 2,
            period = 12,
            balance = BigDecimal(1234567L),
            name = "Кредит №2"
        ),
        Deposit(
            id = 4,
            type = FinancialType.DEPOSIT,
            openDate = System.currentTimeMillis() - 4000 * 60 * 60 * 24 * 15,
            percentRate = 28.5,
            percentType = 2,
            period = 12,
            balance = BigDecimal(1234567L),
            name = "Вклад №2"
        ),
        Credit(
            id = 5,
            type = FinancialType.CREDIT,
            openDate = System.currentTimeMillis() - 5000 * 60 * 60 * 24 * 15,
            percentRate = 149.5,
            percentType = 2,
            period = 12,
            balance = BigDecimal(1234567L),
            name = "Кредит №2"
        ),
        Deposit(
            id = 6,
            type = FinancialType.DEPOSIT,
            openDate = System.currentTimeMillis() - 6000 * 60 * 60 * 24 * 15,
            percentRate = 18.5,
            percentType = 2,
            period = 12,
            balance = BigDecimal(1234567L),
            name = "Вклад №2"
        ),
    )
}