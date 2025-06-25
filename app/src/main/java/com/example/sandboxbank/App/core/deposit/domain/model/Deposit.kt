package com.example.sandboxbank.App.core.deposit.domain.model

data class Deposit(
    override val id: Long,
    override val type: String,
    override val openDate: Long,
    override val percentRate: Double,
    override val percentType: Long,
    override val period: Long,
    override val balance: Long,
    override val name: String = ""
) : FinancialItem()