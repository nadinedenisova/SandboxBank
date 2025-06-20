package com.example.sandboxbank.App.core.deposit.domain.model

data class Deposit(
    override val id: Int,
    override val type: String,
    override val openDate: Long,
    override val percentRate: Double,
    override val percentType: Int,
    override val period: Int,
    override val balance: Double,
    override val name: String = ""
) : FinancialItem()