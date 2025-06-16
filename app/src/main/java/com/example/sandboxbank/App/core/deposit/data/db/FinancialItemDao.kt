package com.example.sandboxbank.App.core.deposit.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FinancialItemDao {

    @Insert
    suspend fun insert(entity: FinancialEntity)

    @Query("SELECT * FROM financial_items WHERE id = :id")
    fun getById(id: String): Flow<FinancialEntity?>

    // Количество депозитов/кредитов
    @Query("SELECT COUNT(*) FROM financial_items WHERE type = :type")
    fun getCountByType(type: String): Flow<Int>

    // Сумма открытых депозитов/кредитов
    @Query("SELECT IFNULL(SUM(balance), 0) FROM financial_items WHERE type = :type")
    fun getSumByType(type: String): Flow<Long>

    // Изменить баланс на указанную величину
    @Query("UPDATE financial_items SET balance = balance + :delta WHERE id = :id")
    suspend fun changeBalanceById(id: Int, delta: Long)

    // Список депозитов/кредитов
    @Query("SELECT * FROM financial_items WHERE type = :type")
    fun getAllByType(type: String): Flow<List<FinancialEntity>>
}