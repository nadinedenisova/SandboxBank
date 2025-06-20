package com.example.sandboxbank.pinCode.ui.intent

sealed interface PinCodeIntent {
    data class AddDigit(val digit: String) : PinCodeIntent
    data object DeleteDigit : PinCodeIntent
    //data object CheckPin : PinCodeIntent
}