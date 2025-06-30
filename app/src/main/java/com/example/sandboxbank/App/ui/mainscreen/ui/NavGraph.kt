package com.example.sandboxbank.App.ui.mainscreen.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sandboxbank.App.core.deposit.data.FinancialType
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.ui.financialScreen.domain.FinancialScreenViewModel
import com.example.sandboxbank.App.ui.financialScreen.ui.FinancialScreenContent
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsViewModel
import com.example.sandboxbank.App.ui.mainscreen.data.Routes
import com.example.sandboxbank.credit.ui.CreditScreen
import com.example.sandboxbank.deposit.ui.DepositScreen
import com.example.sandboxbank.profile.domain.ProfileScreenViewModel
import com.example.sandboxbank.profile.ui.ProfileScreen
import java.math.BigDecimal

@Composable
fun NavGraph(
    navHostController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    startDestination: String = Routes.Cards.route // Используем route вместо name
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Routes.Cards.name) {
            CardsScreen()
        }
        composable(route = Routes.Finance.name) {
            val financialViewModel: FinancialScreenViewModel = viewModel(
                factory = viewModelFactory
            )
            FinancialScreenContent(
                financialViewModel,
                onDepositClick = {
                    navHostController.navigate(Routes.Deposit.route)
                },
                onCreditClick = {
                    navHostController.navigate(Routes.Credit.route)
                },
            )
        }
        composable(route = Routes.History.name) {
            HistoryScreen()
        }
        composable(route = Routes.Transfers.name) {
            TransfersScreen()
        }
        composable(route = Routes.Deposit.route) { backStackEntry ->
            val depositId = backStackEntry.arguments?.getString("depositId")
            Log.i("sandboxbank", "Loading deposit id=$depositId")
            val viewModel: FinancialItemDetailsViewModel = viewModel(
                factory = viewModelFactory
            )

            DepositScreen(
                viewModel = viewModel,
                deposit = Deposit(
                    id = 123,
                    type = FinancialType.DEPOSIT,
                    openDate = 1234L,
                    percentRate = 8.5,
                    percentType = 2,
                    period = 12,
                    balance = BigDecimal(1234567L),
                    name = "Вклад №1"
                ),
                navController = navHostController,
            )
        }

        composable(route = Routes.Credit.route) { backStackEntry ->
            val creditId = backStackEntry.arguments?.getString("creditId")
            Log.i("sandboxbank", "Loading deposit id=$creditId")
            val viewModel: FinancialItemDetailsViewModel = viewModel(
                factory = viewModelFactory
            )
            CreditScreen(
                viewModel = viewModel,
                credit = Credit(
                    id = 123,
                    type = FinancialType.CREDIT,
                    openDate = 1234L,
                    percentRate = 8.5,
                    percentType = 2,
                    period = 12,
                    balance = BigDecimal(1234567L),
                    name = "Кредит №1"
                ),
                navController = navHostController
            )
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
