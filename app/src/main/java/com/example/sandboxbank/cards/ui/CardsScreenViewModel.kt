package com.example.sandboxbank.cards.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import com.example.sandboxbank.cardmanager.cards.debit.model.data.RemoteCardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardsScreenViewModel @Inject constructor(
    private val cardRepository: RemoteCardRepository,
    private val internetUtil: InternetUtil,
): ViewModel() {
    private val _uiState = MutableStateFlow<CardsListUIState>(
        CardsListUIState.Loading
    )
    val uiState: StateFlow<CardsListUIState> = _uiState

    fun loadCards() {

        viewModelScope.launch {
            delay(500)
            val result = cardRepository.getAllCards()
            if (result.isNotEmpty()) {
               _uiState.value = CardsListUIState.Success(result)
            } else {
               _uiState.value = CardsListUIState.SuccessEmpty
            }
        }


    }

    fun retryLoading() {
        loadCards()
    }

}