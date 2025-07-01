package com.example.sandboxbank.App.ui.applycredit.entity

sealed interface ApplyCreditResponse {
    data object Success : ApplyCreditResponse
    data object NoConnection : ApplyCreditResponse
    data object CreditAmountLimit : ApplyCreditResponse
}