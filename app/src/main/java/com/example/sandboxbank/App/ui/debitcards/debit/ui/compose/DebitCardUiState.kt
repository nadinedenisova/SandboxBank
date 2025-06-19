package com.example.sandboxbank.App.ui.debitcards.debit.ui.compose

import com.example.sandboxbank.cardmanager.cards.entity.Card


data class DebitCardUiState(
    val isLoading: Boolean = false,
    val card: Card? = null,
    val isLimitReached: Boolean = false,
    val error: String? = null,
    val isCardCreated: Boolean = false
)
