package com.example.sandboxbank.App.ui.financialitem

import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem

sealed class FinancialItemDetailsUIState {
    object Loading : FinancialItemDetailsUIState()
    data class Success(val data: FinancialItem) : FinancialItemDetailsUIState()
    object Error : FinancialItemDetailsUIState()
}