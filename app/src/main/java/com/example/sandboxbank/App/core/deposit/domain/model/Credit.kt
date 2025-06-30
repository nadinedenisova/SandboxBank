package com.example.sandboxbank.App.core.deposit.domain.model

import java.math.BigDecimal

data class Credit(
    override val id: Long,
    override val type: String,
    override val openDate: Long,
    override val percentRate: Double,
    override val percentType: Long,
    override val period: Long,
    override val balance: BigDecimal,
    override val name: String = ""
) : FinancialItem()