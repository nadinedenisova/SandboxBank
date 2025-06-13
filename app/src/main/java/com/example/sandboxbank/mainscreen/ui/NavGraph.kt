package com.example.sandboxbank.mainscreen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sandboxbank.mainscreen.data.Routes

@Composable
fun NavGraph(
    startDestination: String = Routes.Cards.name,
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