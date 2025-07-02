package com.example.sandboxbank.cards.ui

import com.example.sandboxbank.cardmanager.cards.entity.Card

sealed class CardsListUIState {
    object Loading : CardsListUIState()
    data class Success(val data: List<Card>) : CardsListUIState()
    object SuccessEmpty : CardsListUIState()
    object Error : CardsListUIState()
}