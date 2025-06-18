package com.example.sandboxbank.cardmanager.cards.debit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.App.ui.debitcards.debit.model.data.RemoteCardRepository
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import com.example.sandboxbank.cardmanager.cards.debit.intent.CardIntent
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardRepository
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardViewModel(
    private val remoteRepository: RemoteCardRepository,
    private val cardRepository: CardRepository,
    private val userIdProvider: () -> Long,
    private val internetUtil: InternetUtil
) : ViewModel() {

    private val _state = MutableStateFlow(CardState())
    val state: StateFlow<CardState> = _state

    private var requestNumber = 1L

    fun handleIntent(intent: CardIntent) {
        when (intent) {
            is CardIntent.CreateCard -> createCard(intent.request)
        }
    }

    private fun createCard(request: CardRequest) {
        viewModelScope.launch {
            if (!internetUtil.isInternetAvailable()) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error Internet Connection" // Описание необходимо
                )
                return@launch
            }

            _state.value = _state.value.copy(isLoading = true)

            val result = remoteRepository.createDebitCard(
                userId = userIdProvider(),
                currentCardNumber = request.currentCardNumber,
                requestNumber = requestNumber++
            )

            result.onSuccess { card ->
                cardRepository.saveCard(card)

                _state.value = _state.value.copy(
                    isLoading = false,
                    card = card,
                    isLimitReached = false,
                    error = null
                )

            }.onFailure { error ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = error.message
                )
            }
        }

    }
    fun clearCardResult() {
        _state.value = _state.value.copy(card = null)
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}
