package com.example.sandboxbank.cardmanager.cards.debit.ui

import com.example.sandboxbank.cardmanager.cards.entity.Card

sealed class CardState {
    object Idle : CardState()
    object Loading : CardState()
    data class Success(val card: Card) : CardState()
    object LimitReached : CardState()
    data class Error(val message: String) : CardState()
}
