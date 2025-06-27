package com.example.sandboxbank.transaction.ui.model

data class TransactionUiState(
    val selectedTab: Int = 0,
    val debitFrom: String = "",
    val debitTo: String = "",
    val amount: String = "",
    val accounts: List<String> = emptyList(),
    val status: TransactionState? = null
)
