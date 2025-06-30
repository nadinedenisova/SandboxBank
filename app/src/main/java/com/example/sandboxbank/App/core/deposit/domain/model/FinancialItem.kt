package com.example.sandboxbank.App.core.deposit.domain.model

import com.example.sandboxbank.App.core.deposit.data.FinancialType
import java.math.BigDecimal

sealed class FinancialItem {
    abstract val id: Long
    abstract val type: FinancialType
    abstract val openDate: Long
    abstract val percentRate: Double
    abstract val percentType: Long
    abstract val period: Long
    abstract val balance: BigDecimal
    abstract val name: String
}