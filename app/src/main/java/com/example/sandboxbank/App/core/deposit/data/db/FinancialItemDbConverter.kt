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
            financialItem.percentType,
            financialItem.period,
            financialItem.balance
        )
    }

    fun toDomain(financialItem: FinancialEntity): FinancialItem {
        return when (financialItem.type) {
            FinancialType.DEPOSIT.toStringValue() -> Deposit(
                id = financialItem.id,
                type = financialItem.type,
                openDate = financialItem.openDate,
                percentType = financialItem.percentType,
                period = financialItem.period,
                balance = financialItem.balance
            )
            FinancialType.CREDIT.toStringValue() -> Credit(
                id = financialItem.id,
                type = financialItem.type,
                openDate = financialItem.openDate,
                percentType = financialItem.percentType,
                period = financialItem.period,
                balance = financialItem.balance
            )
            else -> throw IllegalArgumentException("Unknown financial item type: ${financialItem.type}")
        }
    }

}
