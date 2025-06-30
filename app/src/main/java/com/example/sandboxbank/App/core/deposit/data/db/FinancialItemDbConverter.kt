package com.example.sandboxbank.App.core.deposit.data.db

import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import java.math.BigDecimal

class FinancialItemDbConverter {
    fun toEntity(financialItem: FinancialItem): FinancialEntity {
        return FinancialEntity(
            financialItem.id,
            financialItem.type,
            financialItem.openDate,
            financialItem.percentRate,
            financialItem.percentType,
            financialItem.period,
            (financialItem.balance * BigDecimal(100.0)).toLong(),
            financialItem.name,
        )
    }

    fun toDomain(financialItem: FinancialEntity): FinancialItem {
        return when (financialItem.type) {
            FinancialType.DEPOSIT.toStringValue() -> Deposit(
                id = financialItem.id,
                type = financialItem.type,
                openDate = financialItem.openDate,
                percentRate = financialItem.percentRate,
                percentType = financialItem.percentType,
                period = financialItem.period,
                balance = financialItem.balance.toBigDecimal()/ BigDecimal(100.0),
                name = financialItem.name,
            )
            FinancialType.CREDIT.toStringValue() -> Credit(
                id = financialItem.id,
                type = financialItem.type,
                openDate = financialItem.openDate,
                percentRate = financialItem.percentRate,
                percentType = financialItem.percentType,
                period = financialItem.period,
                balance = financialItem.balance.toBigDecimal()/ BigDecimal(100.0),
                name = financialItem.name,
            )
            else -> throw IllegalArgumentException("Unknown financial item type: ${financialItem.type}")
        }
    }

}
