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
    return when (this) {
        is CardState.Idle -> DebitCardUiState(
            isLoading = false,
            isLimitReached = false,
            error = null,
            isCardCreated = false
        )
        is CardState.Loading -> DebitCardUiState(
            isLoading = true,
            isLimitReached = false,
            error = null,
            isCardCreated = false
        )
        is CardState.Success -> DebitCardUiState(
            isLoading = false,
            isLimitReached = false,
            error = null,
            isCardCreated = true,
            card = this.card
        )
        is CardState.LimitReached -> DebitCardUiState(
            isLoading = false,
            isLimitReached = true,
            error = null,
            isCardCreated = false
        )
        is CardState.Error -> DebitCardUiState(
            isLoading = false,
            isLimitReached = false,
            error = this.message,
            isCardCreated = false
        )
    }
}
