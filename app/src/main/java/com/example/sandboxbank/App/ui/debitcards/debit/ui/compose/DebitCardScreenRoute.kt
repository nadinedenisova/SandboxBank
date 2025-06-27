package com.example.sandboxbank.App.ui.debitcards.debit.ui.compose

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.sandboxbank.cardmanager.cards.debit.intent.CardIntent
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardState
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardViewModel
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.DebitCardScreen



@RequiresApi(23)
@Composable
fun DebitCardScreenRoute(
    viewModel: CardViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    val currentState = state // копируем в локальную переменную

    val uiState = when (currentState) {
        is CardState.Loading -> DebitCardUiState(
            isLoading = true,
            card = null,
            isLimitReached = false,
            error = null,
            isCardCreated = false
        )
        is CardState.Success -> DebitCardUiState(
            isLoading = false,
            card = currentState.card,
            isLimitReached = false,
            error = null,
            isCardCreated = currentState.card != null
        )
        is CardState.LimitReached -> DebitCardUiState(
            isLoading = false,
            card = null,
            isLimitReached = true,
            error = null,
            isCardCreated = false
        )
        is CardState.Error -> DebitCardUiState(
            isLoading = false,
            card = null,
            isLimitReached = false,
            error = currentState.message,
            isCardCreated = false
        )
        else -> DebitCardUiState()
    }

    DebitCardScreen(
        uiState = uiState,
        onCreateCardClick = {
            viewModel.handleIntent(
                CardIntent.CreateCard(
                    request = CardRequest(
                        123L,
                        currentCardNumber = 123123123L,
                        requestNumber = 123123123L
                    )

            )
            )
        },
        onBackClick = onBackClick,
        onDismissSuccessDialog = {
            viewModel.clearCardResult()
        },
        onClearError = {
            viewModel.clearError()
        }
    )
}
