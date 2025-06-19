package com.example.sandboxbank.App.core.deposit.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "financial_items", indices = [Index(value = ["type"])])
data class FinancialEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val openDate: Long,
    val percentRate: Double,
    val percentType: Int,
    val period: Int,
    val balance: Long,
    val name: String
)