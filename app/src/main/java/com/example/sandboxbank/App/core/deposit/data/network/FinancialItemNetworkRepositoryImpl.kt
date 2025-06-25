package com.example.sandboxbank.App.core.deposit.data.network

import com.example.sandboxbank.Api
import com.example.sandboxbank.App.core.deposit.domain.db.FinancialItemRepository
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import com.example.sandboxbank.App.ui.debitcards.debit.mapper.toLocalModel
import com.example.sandboxbank.CreateCardRequest
import com.example.sandboxbank.CreateCreditRequest
import com.example.sandboxbank.CreateDepositRequest
import com.example.sandboxbank.ProductResponse
import com.example.sandboxbank.cardmanager.cards.entity.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class FinancialItemNetworkRepositoryImpl(
    private val api: Api,
    private val accessTokenProvider: suspend () -> String
): FinancialItemRepository {
    override suspend fun getUserDepositCount(): Flow<Int> {
        TODO()
    }

    override suspend fun getUserDepositTotal(): Flow<Long> {
        TODO()
    }

    override suspend fun getUserCreditCount(): Flow<Int> {
        TODO()
    }

    override suspend fun getUserCreditTotal(): Flow<Long> {
        TODO()
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

    override suspend fun changeBalance(id: Int, delta: Long) {
        TODO()
    }

    override fun getAllDeposits(): Flow<List<FinancialItem>> {
        TODO()
    }

    override fun getById(id: Int): Flow<FinancialItem> {
        TODO()
    }

    override fun getAllCredits(): Flow<List<FinancialItem>> {
        TODO()
    }
}
