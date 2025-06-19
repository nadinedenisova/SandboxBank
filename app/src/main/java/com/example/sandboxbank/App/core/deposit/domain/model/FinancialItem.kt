package com.example.sandboxbank.App.core.deposit.domain.model

sealed class FinancialItem {
    abstract val id: Int
    abstract val type: String
    abstract val openDate: Long
    abstract val percentRate: Double
    abstract val percentType: Int
    abstract val period: Int
    abstract val balance: Long
    abstract val name: String
}