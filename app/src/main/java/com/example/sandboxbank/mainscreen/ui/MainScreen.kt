package com.example.sandboxbank.mainscreen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sandboxbank.mainscreen.data.Routes

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                navHostController = navController,
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        backgroundColor = Color(0xF3, 0xED, 0xF7)
    ) {
        Routes.entries.forEach { it ->
            BottomNavigationItem(
                icon = { Icon(it.icon, contentDescription = "") },
                label = { Text(it.title) },
                selected = currentDestination?.route == it.route,
                onClick = { navController.navigate(it.route) }
            )
        }
    }
}


@Composable
fun NavGraph(
    startDestination: String = "cards",
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Routes.Cards.name) {
            CardsScreen()
        }
        composable(route = Routes.Finance.name) {
            FinanceScreen()
        }
        composable(route = Routes.History.name) {
            HistoryScreen()
        }
        composable(route = Routes.Transfers.name) {
            TransfersScreen()
        }
        composable(route = Routes.Profile.name) {
            ProfileScreen()
        }
    }
}


@Composable
fun CardsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран Карт")
    }
}

@Composable
fun FinanceScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран Финансы")
    }
}

@Composable
fun TransfersScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран Переводы")
    }
}

@Composable
fun HistoryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран История")
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран Профиль")
    }
}