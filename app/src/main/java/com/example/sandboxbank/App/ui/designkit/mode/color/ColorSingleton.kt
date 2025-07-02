package com.example.sandboxbank.App.ui.designkit.mode.color

import androidx.compose.runtime.MutableState
import com.example.sandboxbank.App.ui.designkit.mode.ColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.baseLightPalette
import androidx.compose.runtime.mutableStateOf

object ColorSingleton {
    var appPalette: MutableState<ColorPalette> = mutableStateOf(baseLightPalette)
}