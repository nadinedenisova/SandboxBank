package com.example.sandboxbank.App.ui.financialitem

sealed class FinancialItemDetailsUIState<out T> {
    object Loading : FinancialItemDetailsUIState<Nothing>()
    data class Success<T>(val data: T) : FinancialItemDetailsUIState<T>()
    data class Error(val exception: Throwable) : FinancialItemDetailsUIState<Nothing>()
}