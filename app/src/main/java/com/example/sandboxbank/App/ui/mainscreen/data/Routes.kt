package com.example.sandboxbank.App.ui.mainscreen.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sandboxbank.App.ui.designkit.mode.language.LanguageSingleton
import com.example.sandboxbank.App.ui.designkit.mode.language.getForRoute


enum class Routes(
    val route: String,
    val icon: ImageVector
) {
    Cards(
        route = "cards",
        //title = mutableStateOf(LanguageSingleton.localization.value.profile()),
        icon = Icons.Default.Info
    ),
    Finance(
        route = "finance",
        //title = mutableStateOf(LanguageSingleton.localization.value.profile()),
        icon = Icons.Default.Info
    ),
    Transfers(
        route = "transfers",
       // title = mutableStateOf(LanguageSingleton.localization.value.profile()),
        icon = Icons.Default.Info
    ),
    History(
        route = "history",
        //title = mutableStateOf(LanguageSingleton.localization.value.profile()),
        icon = Icons.Default.Info
    ),
    Profile(
        route = "profile",
        icon = Icons.Default.Info
    ),
}
