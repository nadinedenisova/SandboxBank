package com.example.sandboxbank.loan

import com.example.sandboxbank.loan.ui.LoanDetailsScreenUiState
import com.example.sandboxbank.loan.ui.model.LoanDetailsState

class LoanDetailsUiStateMapper {
    fun map(loanDetailsScreenState: LoanDetailsState): LoanDetailsScreenUiState {
        val titleText = when(loanDetailsScreenState.screenType) {
            LoanDetailsState.ScreenType.SUCCESS -> "Успешно"
        }
        return LoanDetailsScreenUiState(titleText = titleText)
    }
}