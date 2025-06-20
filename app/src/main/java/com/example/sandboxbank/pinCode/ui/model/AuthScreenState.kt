package com.example.sandboxbank.pinCode.ui.model

data class AuthScreenState(
    val pinCode: String = "",
    val screenType: ScreenType,
    val confirmPin: String = "",
    val hasError: Boolean = false
) {
    enum class ScreenType {
        SIGN_UP,
        SIGN_IN,
        INCORRECT_PIN,
        TRY_PIN,
        SUCCESS
    }
}


