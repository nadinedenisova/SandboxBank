package com.example.sandboxbank.transaction.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.transaction.data.repository.TransactionRepository
import com.example.sandboxbank.transaction.ui.model.TransactionState
import com.example.sandboxbank.transaction.ui.model.TransactionUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        TransactionUiState(
            accounts = listOf("Дебетовая карта", "Кредитная карта", "Вклад", "Кредит"),
            debitFrom = "Дебетовая карта"
        )
    )
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    fun onTabSelected(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }

    fun onDebitFromSelected(value: String) {
        _uiState.update { it.copy(debitFrom = value) }
    }

    fun onDebitToSelected(value: String) {
        _uiState.update { it.copy(debitTo = value) }
    }

    fun onAmountChanged(value: String) {
        _uiState.update { it.copy(amount = value) }
    }

    fun onTransferClick() {
        viewModelScope.launch {
            _uiState.update { it.copy(status = TransactionState.Loading) }

            delay(1000)

            val connected = repository.checkInternetConnection()
            if (!connected) {
                _uiState.update { it.copy(status = TransactionState.NoInternet) }
            } else {
                val currentState = _uiState.value
                if (currentState.amount.isBlank() || currentState.debitTo.isBlank()) {
                    _uiState.update { it.copy(status = TransactionState.Error("Заполните все поля")) }
                } else {
                    _uiState.update { it.copy(status = TransactionState.Success) }
                }
            }
        }
    }

    fun resetStatus() {
        _uiState.update { it.copy(status = null) }
    }
}

