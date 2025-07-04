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
import com.example.sandboxbank.App.ui.mainscreen.data.Routes

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        backgroundColor = Color(0xF3, 0xED, 0xF7)
    ) {
        Routes.entries
            .filter { it.icon != null }
            .forEach { it ->
            BottomNavigationItem(
                icon = { Icon(it.icon!!, contentDescription = "") },
                label = { Text(it.title) },
                selected = currentDestination?.route == it.route,
                onClick = { navController.navigate(it.route) }
            )
        }
    }
}
