package com.example.sandboxbank.App.ui.debitcards.debit.ui.compose

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.sandboxbank.cardmanager.cards.debit.intent.CardIntent
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

    val uiState = DebitCardUiState(
        isLoading = state.isLoading,
        isLimitReached = state.isLimitReached,
        error = state.error,
        isCardCreated = state.card != null
    )

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
