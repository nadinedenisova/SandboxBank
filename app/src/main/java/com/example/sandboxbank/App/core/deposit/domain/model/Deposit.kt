package com.example.sandboxbank.App.core.deposit.domain.model

import com.example.sandboxbank.App.core.deposit.data.FinancialType
import java.math.BigDecimal

data class Deposit(
    override val id: Long,
    override val type: FinancialType,
    override val openDate: Long,
    override val percentRate: Double,
    override val percentType: Long,
    override val period: Long,
    override val balance: BigDecimal,
    override val name: String = ""
) : FinancialItem()