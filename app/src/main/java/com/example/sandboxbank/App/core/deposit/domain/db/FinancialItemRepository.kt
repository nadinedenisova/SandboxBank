package com.example.sandboxbank.App.core.deposit.domain.db

import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import kotlinx.coroutines.flow.Flow

interface FinancialItemRepository {
    suspend fun getUserDepositCount(): Flow<Int>

    suspend fun getUserDepositTotal(): Flow<Long>

    suspend fun getUserCreditCount(): Flow<Int>

    suspend fun getUserCreditTotal(): Flow<Long>

    suspend fun insert(financialItem: FinancialItem)

    suspend fun changeBalance(id: Int, delta: Long)

    fun getAllDeposits(): Flow<List<FinancialItem>>

    fun getById(id: Int): Flow<FinancialItem>

    fun getAllCredits(): Flow<List<FinancialItem>>
}