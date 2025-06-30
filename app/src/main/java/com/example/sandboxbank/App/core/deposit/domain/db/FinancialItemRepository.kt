package com.example.sandboxbank.App.core.deposit.domain.db

import com.example.sandboxbank.App.core.deposit.data.UserFinancialStats
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import kotlinx.coroutines.flow.Flow

interface FinancialItemRepository {

    suspend fun getUserFinancialStats(userId: Long): Result<UserFinancialStats>

    suspend fun insert(userId: Long, financialItem: FinancialItem, requestNumber: Long): Result<FinancialItem>

    suspend fun changeBalance(id: Int, delta: Long)

    suspend fun getAllDeposits(userId: Long): Result<List<FinancialItem>>

    suspend fun getById(id: Long, userId: Long): Result<FinancialItem>

    suspend fun getAllCredits(userId: Long): Result<List<FinancialItem>>

    suspend fun getAllProducts(userId: Long): Result<List<FinancialItem>>
}