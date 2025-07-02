package com.example.sandboxbank.App.ui.designkit.mode.language

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object LanguageSingleton {
    var localization: MutableState<Localization> = mutableStateOf(defaultLocalization)
}