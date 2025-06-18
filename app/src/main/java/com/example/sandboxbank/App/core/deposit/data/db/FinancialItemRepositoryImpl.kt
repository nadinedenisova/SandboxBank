package com.example.sandboxbank.App.core.deposit.data.db

import com.example.sandboxbank.App.core.deposit.domain.db.FinancialItemRepository
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FinancialItemRepositoryImpl(private val financialItemDao: FinancialItemDao,
                                  private val converter: FinancialItemDbConverter
): FinancialItemRepository {

    override suspend fun getUserDepositCount(): Flow<Int> {
        return financialItemDao.getCountByType(FinancialType.DEPOSIT.toStringValue())
    }

    override suspend fun getUserDepositTotal(): Flow<Long> {
        return financialItemDao.getSumByType(FinancialType.DEPOSIT.toStringValue())
    }

    override suspend fun getUserCreditCount(): Flow<Int> {
        return financialItemDao.getCountByType(FinancialType.CREDIT.toStringValue())
    }

    override suspend fun getUserCreditTotal(): Flow<Long> {
        return financialItemDao.getSumByType(FinancialType.CREDIT.toStringValue())
    }

    override suspend fun insert(item: FinancialItem) {
        financialItemDao.insert(converter.toEntity(item))
    }

    override suspend fun changeBalance(id: Int, delta: Long) {
        financialItemDao.changeBalanceById(id, delta)
    }

    // Получить все депозиты
    override fun getAllDeposits(): Flow<List<FinancialItem>> {
        return financialItemDao.getAllByType(FinancialType.DEPOSIT.toStringValue())
            .map { entities ->
                entities.map { entity ->
                    FinancialItemDbConverter().toDomain(entity)
                }
            }
    }

    // Получить все кредиты
    override fun getAllCredits(): Flow<List<FinancialItem>> {
        return financialItemDao.getAllByType(FinancialType.CREDIT.toStringValue())
            .map { entities ->
                entities.map { entity ->
                    FinancialItemDbConverter().toDomain(entity)
                }
            }
    }
}