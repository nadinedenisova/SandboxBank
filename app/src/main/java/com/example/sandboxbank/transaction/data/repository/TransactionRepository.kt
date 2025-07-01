package com.example.sandboxbank.transaction.data.repository

interface TransactionRepository {
    suspend fun checkInternetConnection(): Boolean

    suspend fun makeTransfer(
        from: String,
        to: String,
        amount: Double,
        type: String
    ): Result<Unit>
}