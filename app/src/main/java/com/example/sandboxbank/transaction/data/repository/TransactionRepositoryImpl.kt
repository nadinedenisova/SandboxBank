package com.example.sandboxbank.transaction.data.repository

import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor() : TransactionRepository {
    override suspend fun checkInternetConnection(): Boolean {
        TODO()
    }
}