package com.example.sandboxbank.cardmanager.cards.dto

import com.example.sandboxbank.cardmanager.cards.entity.Card

data class CardResponse(
    val currentCardNumber: Long,
    val requestNumber: Long,
    val card: Card
)
