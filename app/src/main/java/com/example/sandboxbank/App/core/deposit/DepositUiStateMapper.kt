package com.example.sandboxbank.App.core.deposit

import com.example.sandboxbank.App.core.deposit.ui.DepositScreenUiState
import com.example.sandboxbank.App.core.deposit.ui.model.DepositState

class DepositUiStateMapper {
    fun map(depositScreenState: DepositState): DepositScreenUiState {
        val titleText = when(depositScreenState.screenType) {
            DepositState.ScreenType.SUCCESS -> "Успешно"
        }
        return DepositScreenUiState(titleText = titleText)
    }
}