package com.example.sandboxbank.App.core.deposit.data

data class UserFinancialStats(
    val depositCount: Int,
    val depositTotal: Long,
    val creditCount: Int,
    val creditTotal: Long
)