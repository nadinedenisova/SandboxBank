package com.example.sandboxbank.credit.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailScreen
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsViewModel
import com.example.sandboxbank.R

@Composable
fun CreditScreen(creditId: Long, navController: NavController, viewModel: FinancialItemDetailsViewModel) {
    FinancialItemDetailScreen(
        financialItemId = creditId,
        navController = navController,
        viewModel,
        screenTitle = stringResource(R.string.credit),
        addMoneyText = stringResource(R.string.credit_add_money),
        closeItemText = stringResource(R.string.credit_close),
        onAddMoneyClick = {},
        onCloseItemClick = {},
    )
}

//@Composable
//@Preview
//fun CreditScreenPreview() {
//    CreditScreen(Credit(
//        id = 123,
//        type = FinancialType.CREDIT,
//        openDate = 1234L,
//        percentRate = 49.5,
//        percentType = 2,
//        period = 12,
//        balance = BigDecimal(1234567L),
//        name = "Кредит №1"
//    ), rememberNavController()
//    )
//}