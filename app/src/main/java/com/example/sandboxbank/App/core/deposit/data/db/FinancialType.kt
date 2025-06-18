package com.example.sandboxbank.App.core.deposit.data.db

enum class FinancialType {
    DEPOSIT,
    CREDIT,
    CARD_DEBIT,
    CARD_CREDIT;

    // Для сохранения в БД как String
    fun toStringValue(): String = name
    companion object {
        fun fromString(value: String) = valueOf(value)
    }
}