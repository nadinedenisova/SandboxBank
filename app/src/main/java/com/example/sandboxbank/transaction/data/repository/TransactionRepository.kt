package com.example.sandboxbank.transaction.data.repository

interface TransactionRepository {
    suspend fun checkInternetConnection(): Boolean
}