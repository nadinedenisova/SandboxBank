package com.example.sandboxbank.cardmanager.cards.debit.intent

import com.example.sandboxbank.cardmanager.cards.dto.CardRequest


sealed class CardIntent {
    data class CreateCard(val request: CardRequest) : CardIntent()
}
