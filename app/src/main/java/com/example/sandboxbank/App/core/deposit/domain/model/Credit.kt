package com.example.sandboxbank.App.core.deposit.domain.model

data class Credit(
    override val id: Int,
    override val type: String,
    override val openDate: Long,
    override val percentRate: Double,
    override val percentType: Int,
    override val period: Int,
    override val balance: Long,
    override val name: String = ""
) : FinancialItem()