package com.example.sandboxbank.App.ui.debitcards.debit.mapper

import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.DebitCardUiState
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardState
import com.example.sandboxbank.Card as ApiCard
import com.example.sandboxbank.cardmanager.cards.entity.Card as LocalCard

fun ApiCard.toLocalModel(): LocalCard = LocalCard(
    id = this.id,
    cvv = this.cvv.toLong(),
    endDate = this.endDate,
    owner = this.owner,
    type = this.type,
    percent = this.percent,
    balance = this.balance.toLong()
)

fun CardState.toUiState(): DebitCardUiState {
    return DebitCardUiState(
        isLoading = this.isLoading,
        card = this.card,
        isLimitReached = this.isLimitReached,
        error = this.error,
        isCardCreated = this.card != null
    )
}

