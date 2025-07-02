package com.example.sandboxbank.transaction.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.sandboxbank.Api
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import com.example.sandboxbank.transaction.data.dto.TransferRequest
import java.io.IOException
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val api: Api,
    private val internetUtil: InternetUtil,
    private val tokenProvider: suspend () -> String
) : TransactionRepository {

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun checkInternetConnection(): Boolean {
        return internetUtil.isInternetAvailable()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun makeTransfer(
        from: String,
        to: String,
        amount: Double,
        type: String
    ): Result<Unit> {
        return try {
            if (!checkInternetConnection()) {
                return Result.failure(NoInternetException())
            }

            val token = tokenProvider()
            val response = api.makeTransfer(
                TransferRequest(from, to, amount, type),
                "Bearer $token"
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(ServerErrorException(response.code(), response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class NoInternetException : IOException("Нет подключения к интернету")
class ServerErrorException(val code: Int, override val message: String) : IOException()
