package com.example.sandboxbank.deposit.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "financial_items", indices = [Index(value = ["type"])])
data class FinancialEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Вклад / кредит
    val type: String,

    // Дата открытия (Unix timestamp)
    val openDate: Long,

    // Процентная ставка
    val percentType: Int,

    // Срок в месяцах
    val period: Int,

    // Остаток средств
    val balance: Long
)