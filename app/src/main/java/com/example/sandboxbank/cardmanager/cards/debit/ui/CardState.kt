package com.example.sandboxbank.cardmanager.cards.debit.ui


import com.example.sandboxbank.cardmanager.cards.entity.Card

data class CardState(
    val isLoading: Boolean = false,
    val card: Card? = null,
    val error: String? = null,
    val isLimitReached: Boolean = false
)
