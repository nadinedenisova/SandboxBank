package com.example.sandboxbank.pinCode

import com.example.sandboxbank.pinCode.ui.AuthScreenUiState
import com.example.sandboxbank.pinCode.ui.model.AuthScreenState

class AuthScreenUiStateMapper {
    fun map(authScreenState: AuthScreenState): AuthScreenUiState {
        val titleText = when(authScreenState.screenType) {
            AuthScreenState.ScreenType.SIGN_UP -> "Введите пин код для регистрации"
            AuthScreenState.ScreenType.SIGN_IN -> "Введите пин код для авторизации"
            AuthScreenState.ScreenType.INCORRECT_PIN -> "Неправильный пин"
            AuthScreenState.ScreenType.TRY_PIN -> "Введите еще раз"
            AuthScreenState.ScreenType.SUCCESS -> "Успешно"
        }
        return AuthScreenUiState(pinCodeValue = authScreenState.pinCode, titleText = titleText)
    }
}
