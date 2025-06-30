package com.example.sandboxbank.App.core.deposit.data.network

import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import com.example.sandboxbank.Product
import java.math.BigDecimal

fun Product.toLocalModel(): FinancialItem = when (type) {
    "DEPOSIT" -> Deposit(
        id = id,
        type = type,
        openDate = 0L, // TODO: получать из API, если доступен
        percentRate = percent.toDouble(),
        percentType = percentType,
        period = period,
        balance = balance.toBigDecimal()/BigDecimal(100.00),
        name = "Deposit $id"
    )
    "CREDIT" -> Credit(
        id = id,
        type = type,
        openDate = 0L, // TODO: тоже нужно получить, если возможно
        percentRate = percent.toDouble(),
        percentType = percentType,
        period = period,
        balance = balance.toBigDecimal()/BigDecimal(100.00),
        name = "Credit $id"
    )
    else -> throw IllegalArgumentException("Unknown financial item type: $type")
}