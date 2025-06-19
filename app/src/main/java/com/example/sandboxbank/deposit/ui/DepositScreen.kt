package com.example.sandboxbank.deposit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sandboxbank.App.core.deposit.data.db.FinancialType
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.ui.financialitem.FinancialItemActionButton
import com.example.sandboxbank.App.ui.financialitem.FinancialItemActionRow
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsUIState
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsViewModel
import com.example.sandboxbank.App.ui.financialitem.FinancialItemInfoCard
import com.example.sandboxbank.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositScreen(deposit: Deposit, navController: NavController) {
    val viewModel: FinancialItemDetailsViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadFinancialItem(deposit.id)
    }

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_back_arrow_16x16),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                }},

                title = { Text(
                    text = stringResource(R.string.deposit),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
//            when (uiState) {
//
//            }

            // Блок с названием вклада и суммой
            FinancialItemInfoCard(deposit)
            // Кнопки действий
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 34.dp, end = 34.dp, top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FinancialItemActionButton(
                    text = stringResource(R.string.deposit_add_money),
                    onClick = { /* Пополнение */ },
                    modifier = Modifier.weight(1f)
                )
                FinancialItemActionButton(
                    text = stringResource(R.string.deposit_close),
                    onClick = { /* Закрытие вклада */ },
                    modifier = Modifier.weight(1f))
            }

            /* операции по вкладу */
            FinancialItemActionRow(iconId = R.drawable.icon_operations, text = stringResource(R.string.deposit_operations), onClick = {})

            /* начисленные проценты */
            FinancialItemActionRow(iconId = R.drawable.icon_percent, text = stringResource(R.string.deposit_percent_list), onClick = {})
        }
    }
}

@Composable
@Preview
fun DepositScreenPreview() {
    DepositScreen(Deposit(
        id = 123,
        type = FinancialType.DEPOSIT.toStringValue(),
        openDate = 1234L,
        percentRate = 8.5,
        percentType = 2,
        period = 12,
        balance = 1234567.0,
        name = "Вклад №1"
    ), rememberNavController()
    )
}