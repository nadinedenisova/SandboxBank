package com.example.sandboxbank.transaction.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.App.ui.designkit.mode.language.LanguageSingleton
import com.example.sandboxbank.transaction.data.repository.NoInternetException
import com.example.sandboxbank.transaction.data.repository.TransactionRepository
import com.example.sandboxbank.transaction.ui.model.TransactionState
import com.example.sandboxbank.transaction.ui.model.TransactionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.sandboxbank.App.ui.designkit.mode.language.*

class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        TransactionUiState(
            accounts = listOf(LanguageSingleton.localization.value.debitCard(), LanguageSingleton.localization.value.creditCard(), LanguageSingleton.localization.value.depositCard(), LanguageSingleton.localization.value.credit()),
            debitFrom = LanguageSingleton.localization.value.debitCard()
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

    fun onTransactionClick() {
        viewModelScope.launch {
            _uiState.update { it.copy(status = TransactionState.Loading) }

            val currentState = _uiState.value
            val connected = repository.checkInternetConnection()
            if (!connected) {
                _uiState.update { it.copy(status = TransactionState.NoInternet) }
                return@launch
            }

            val amountDouble = currentState.amount.toDoubleOrNull()
            if (currentState.debitFrom.isBlank() ||
                currentState.debitTo.isBlank() ||
                amountDouble == null
            ) {
                _uiState.update {
                    it.copy(status = TransactionState.Error(LanguageSingleton.localization.value.transferInfo()))
                }
                return@launch
            }

            val type = when (currentState.selectedTab) {
                0 -> "internal"
                1 -> "toUser"
                2 -> "other"
                else -> "internal"
            }

            val result = repository.makeTransfer(
                currentState.debitFrom,
                currentState.debitTo,
                amountDouble,
                type
            )

            if (result.isSuccess) {
                _uiState.update { it.copy(status = TransactionState.Success) }
            } else {
                val exception = result.exceptionOrNull()
                if (exception is NoInternetException) {
                    _uiState.update { it.copy(status = TransactionState.NoInternet) }
                } else {
                    _uiState.update {
                        it.copy(
                            status = TransactionState.Error(
                                exception?.localizedMessage ?: LanguageSingleton.localization.value.transferError()
                            )
                        )
                    }
                }
            }
        }
    }



    fun resetStatus() {
        _uiState.update { it.copy(status = null) }
    }
}

