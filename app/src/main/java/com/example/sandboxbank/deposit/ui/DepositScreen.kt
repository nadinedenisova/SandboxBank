package com.example.sandboxbank.deposit.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailScreen
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsViewModel
import com.example.sandboxbank.R

@Composable
fun DepositScreen(depositId: Long, navController: NavController, viewModel: FinancialItemDetailsViewModel) {
    FinancialItemDetailScreen(
        financialItemId = depositId,
        navController = navController,
        viewModel = viewModel,
        screenTitle = stringResource(R.string.deposit),
        addMoneyText = stringResource(R.string.deposit_add_money),
        closeItemText = stringResource(R.string.deposit_close),
        onAddMoneyClick = {},
        onCloseItemClick = {},
    )
}

//@Composable
//@Preview
//fun DepositScreenPreview() {
//    DepositScreen(Deposit(
//        id = 123,
//        type = FinancialType.DEPOSIT,
//        openDate = 1234L,
//        percentRate = 8.5,
//        percentType = 2,
//        period = 12,
//        balance = BigDecimal(1234567L),
//        name = "Вклад №1"
//    ),
//        rememberNavController(),
//        viewModel = TODO(),
//    )
//}