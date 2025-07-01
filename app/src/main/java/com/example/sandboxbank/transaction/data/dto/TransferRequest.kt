package com.example.sandboxbank.transaction.data.dto

data class TransferRequest(
    val fromAccount: String,
    val toAccount: String,
    val amount: Double,
    val type: String
)
