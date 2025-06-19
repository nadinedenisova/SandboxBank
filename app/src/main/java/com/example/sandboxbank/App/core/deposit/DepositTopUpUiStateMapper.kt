package com.example.sandboxbank.App.core.deposit

import com.example.sandboxbank.App.core.deposit.ui.DepositTopUpScreenUiState
import com.example.sandboxbank.App.core.deposit.ui.model.DepositTopUpState

class DepositTopUpUiStateMapper {
    fun map(depositScreenState: DepositTopUpState): DepositTopUpScreenUiState {
        val titleText = when(depositScreenState.screenType) {
            DepositTopUpState.ScreenType.SUCCESS -> "Успешно"
        }
        return DepositTopUpScreenUiState(titleText = titleText)
    }
}