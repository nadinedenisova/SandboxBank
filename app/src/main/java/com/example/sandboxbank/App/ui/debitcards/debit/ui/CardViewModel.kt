package com.example.sandboxbank.App.ui.debitcards.debit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.cardmanager.cards.debit.intent.CardIntent
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardRepository
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardState
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardViewModel(
    private val repository: CardRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CardState())
    val state: StateFlow<CardState> = _state

    fun handleIntent(intent: CardIntent) {
        when (intent) {
            is CardIntent.CreateCard -> createCard(intent.request)
        }
    }

    private fun createCard(request: CardRequest) {
        if (repository.getCardCount() >= 5) {
            _state.value = _state.value.copy(isLimitReached = true)
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            repository.createCard(request).collect { result ->
                result.onSuccess { response ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        card = response.card,
                        isLimitReached = false
                    )
                }.onFailure { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
        }
    }
}
