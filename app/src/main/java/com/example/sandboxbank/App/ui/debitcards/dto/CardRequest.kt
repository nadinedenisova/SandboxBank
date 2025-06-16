package com.example.sandboxbank.cardmanager.cards.dto


data class CardRequest(
    val userId: Long,
    val currentCardNumber: Long,
    val requestNumber: Long
)
