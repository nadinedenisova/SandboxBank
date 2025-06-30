package com.example.sandboxbank.cardmanager.cards.debit.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import com.example.sandboxbank.cardmanager.cards.debit.intent.CardIntent
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardCreationResult
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardRepository
import com.example.sandboxbank.cardmanager.cards.debit.model.data.RemoteCardRepository
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import jakarta.inject.Inject

class CardViewModel @Inject constructor(
    private val remoteRepository: RemoteCardRepository,
    private val cardRepository: CardRepository,
    private val userIdProvider: () -> Long,
    private val internetUtil: InternetUtil
) : ViewModel() {

    private val _state = MutableStateFlow<CardState>(CardState.Idle)
    val state: StateFlow<CardState> = _state

    private var requestNumber = 1L

    @RequiresApi(Build.VERSION_CODES.M)
    fun handleIntent(intent: CardIntent) {
        when (intent) {
            is CardIntent.CreateCard -> createCard(intent.request)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun createCard(request: CardRequest) {
        viewModelScope.launch {
            if (!internetUtil.isInternetAvailable()) {
                _state.value = CardState.Error("Нет подключения к интернету")
                return@launch
            }

            _state.value = CardState.Loading

            when (val result = remoteRepository.createDebitCard(
                userId = userIdProvider(),
                currentCardNumber = request.currentCardNumber,
                requestNumber = requestNumber++
            )) {
                is CardCreationResult.Success -> {
                    cardRepository.saveCard(result.card)
                    _state.value = CardState.Success(result.card)
                }

                is CardCreationResult.LimitReached -> {
                    _state.value = CardState.LimitReached
                }

                is CardCreationResult.Error -> {
                    _state.value = CardState.Error(result.message)
                }
            }
        }
    }

    fun clearCardResult() {
        _state.value = CardState.Idle
    }

    fun clearError() {
        if (_state.value is CardState.Error || _state.value is CardState.LimitReached) {
            _state.value = CardState.Idle
        }
    }
}
