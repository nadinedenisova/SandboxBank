package com.example.sandboxbank.App.core.deposit.domain.model

data class Deposit(
    override val id: Int,
    override val type: String,
    override val openDate: Long,
    override val percentType: Int,
    override val period: Int,
    override val balance: Long
) : FinancialItem()