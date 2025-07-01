package com.example.sandboxbank.App.ui.financialScreen.data

import androidx.annotation.StringRes
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit

sealed interface FinanceState {
    object Loading : FinanceState
    data class ContentDeposits(
        val deposits: List<Deposit>
    ) : FinanceState

    data class ContentCredits(
        val credits: List<Credit>
    ) : FinanceState

    data class Error(
        @StringRes val errorMessageId: Int
    ): FinanceState


}