package com.example.sandboxbank.App.ui.mainscreen.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

enum class Routes(
    val route: String,
    val title: String,
    val icon: ImageVector?
) {
    Cards(
        route = "cards",
        title = "Карты",
        icon = Icons.Default.Info
    ),
    Finance(
        route = "finance",
        title = "Финансы",
        icon = Icons.Default.Info
    ),
    Transfers(
        route = "transfers",
        title = "Переводы",
        icon = Icons.Default.Info
    ),
    History(
        route = "history",
        title = "История",
        icon = Icons.Default.Info
    ),
    Profile(
        route = "profile",
        title = "Профиль",
        icon = Icons.Default.Info
    ),
    Deposit(
        route = "deposit/{depositId}",
        title = "Вклад",
        icon = null
    ),
    Credit(
        route = "credit/{creditId}",
        title = "Кредит",
        icon = null
    ),
}