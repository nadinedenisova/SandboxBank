package com.example.sandboxbank.pinCode

import com.example.sandboxbank.pinCode.ui.model.AuthScreenState
import com.example.sandboxbank.pinCode.ui.model.AuthScreenUiState

class AuthScreenUiStateMapper {
    fun map(authScreenState: AuthScreenState): AuthScreenUiState {
        val titleText = when(authScreenState.screenType) {
            AuthScreenState.ScreenType.SIGN_UP -> "Придумайте ПИН-код"
            AuthScreenState.ScreenType.SIGN_IN -> "Введите ПИН-код"
            AuthScreenState.ScreenType.INCORRECT_PIN -> "Введите ПИН-код"
            AuthScreenState.ScreenType.TRY_PIN -> "Введите еще раз"
            AuthScreenState.ScreenType.SUCCESS -> "Успешно"
        }
        val pinDotsType = if (authScreenState.hasError) PinDotsType.INCORRECT else PinDotsType.DEFAULT

        return AuthScreenUiState(pinCodeValue = authScreenState.pinCode, titleText = titleText, pinDotsType = pinDotsType)
    }
}

enum class PinDotsType {
    DEFAULT,
    INCORRECT,
    SUCCESS
}
