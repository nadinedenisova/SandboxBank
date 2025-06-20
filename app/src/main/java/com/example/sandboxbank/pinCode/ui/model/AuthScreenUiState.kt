package com.example.sandboxbank.pinCode.ui.model

import com.example.sandboxbank.pinCode.PinDotsType

data class AuthScreenUiState(
    val pinCodeValue: String = "",
    val titleText: String,
    val pinDotsType: PinDotsType,
    val hasError: Boolean = false
)