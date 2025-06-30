package com.example.sandboxbank.App.core.deposit.domain.model

sealed class FinancialItem {
    abstract val id: Long
    abstract val type: String
    abstract val openDate: Long
    abstract val percentRate: Double
    abstract val percentType: Long
    abstract val period: Long
    abstract val balance: Long
    abstract val name: String
}