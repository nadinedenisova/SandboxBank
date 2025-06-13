package com.example.sandboxbank.deposit.data.db

enum class FinancialType {
    DEPOSIT,
    CREDIT;

    // Для сохранения в БД как String
    fun toStringValue(): String = name
    companion object {
        fun fromString(value: String) = valueOf(value)
    }
}