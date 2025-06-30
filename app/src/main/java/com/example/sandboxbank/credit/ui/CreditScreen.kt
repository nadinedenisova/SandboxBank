package com.example.sandboxbank.credit.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sandboxbank.App.core.deposit.data.db.FinancialType
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailScreen
import com.example.sandboxbank.R

@Composable
fun CreditScreen(credit: Credit, navController: NavController) {
    FinancialItemDetailScreen(
        financialItem = credit,
        navController = navController,
        screenTitle = stringResource(R.string.credit),
        addMoneyText = stringResource(R.string.credit_add_money),
        closeItemText = stringResource(R.string.credit_close),
        onAddMoneyClick = {},
        onCloseItemClick = {},
    )
}

@Composable
@Preview
fun CreditScreenPreview() {
    CreditScreen(Credit(
        id = 123,
        type = FinancialType.CREDIT.toStringValue(),
        openDate = 1234L,
        percentRate = 49.5,
        percentType = 2,
        period = 12,
        balance = 1234567L,
        name = "Кредит №1"
    ), rememberNavController()
    )
}