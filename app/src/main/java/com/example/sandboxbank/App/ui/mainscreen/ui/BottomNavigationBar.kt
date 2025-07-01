package com.example.sandboxbank.App.ui.mainscreen.ui

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sandboxbank.App.ui.designkit.mode.color.ColorSingleton
import com.example.sandboxbank.App.ui.designkit.mode.language.LanguageSingleton
import com.example.sandboxbank.App.ui.designkit.mode.language.getForRoute
import com.example.sandboxbank.App.ui.mainscreen.data.Routes

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        backgroundColor = ColorSingleton.appPalette.value.onSurfaceContainer
    ) {
        Routes.entries
            .filter { it.icon != null }
            .forEach { it ->
            BottomNavigationItem(
                icon = { Icon(it.icon!!, contentDescription = "", tint = ColorSingleton.appPalette.value.onSurfaceVariant) },
                label = { Text(LanguageSingleton.localization.value.getForRoute(it.route), color = ColorSingleton.appPalette.value.primaryInverce) },
                selected = currentDestination?.route == it.route,
                onClick = { navController.navigate(it.route) }
            )
        }
    }
}
