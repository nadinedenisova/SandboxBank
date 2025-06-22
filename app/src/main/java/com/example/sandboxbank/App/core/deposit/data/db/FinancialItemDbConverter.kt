package com.example.sandboxbank.App.core.deposit.data.db

import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem

class FinancialItemDbConverter {
    fun toEntity(financialItem: FinancialItem): FinancialEntity {
        return FinancialEntity(
            financialItem.id,
            financialItem.type,
            financialItem.openDate,
            financialItem.percentRate,
            financialItem.percentType,
            financialItem.period,
            financialItem.balance,
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
                balance = financialItem.balance,
                name = financialItem.name,
            )
            FinancialType.CREDIT.toStringValue() -> Credit(
                id = financialItem.id,
                type = financialItem.type,
                openDate = financialItem.openDate,
                percentRate = financialItem.percentRate,
                percentType = financialItem.percentType,
                period = financialItem.period,
                balance = financialItem.balance,
                name = financialItem.name,
            )
            else -> throw IllegalArgumentException("Unknown financial item type: ${financialItem.type}")
        }
    }

}
