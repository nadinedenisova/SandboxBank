package com.example.sandboxbank.transaction.ui.model

sealed class TransactionState {
    object Success : TransactionState()
    object NoInternet : TransactionState()
    object Loading : TransactionState()
    data class Error(val message: String) : TransactionState()
}