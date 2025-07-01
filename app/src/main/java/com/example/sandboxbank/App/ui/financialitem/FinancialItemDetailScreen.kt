package com.example.sandboxbank.App.ui.financialitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items.NoInternetDialog
import com.example.sandboxbank.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancialItemDetailScreen(
    financialItemId: Long,
    navController: NavController,
    viewModel: FinancialItemDetailsViewModel,
    screenTitle: String,
    addMoneyText: String,
    closeItemText: String,
    onAddMoneyClick: () -> Unit,
    onCloseItemClick: () -> Unit,

    ) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFinancialItem(financialItemId)
    }




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
                    text = screenTitle,
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
            when (val state = uiState.value) {
                is FinancialItemDetailsUIState.Loading -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is FinancialItemDetailsUIState.Error -> {
                    NoInternetDialog(
                        onDismiss = {navController.popBackStack()},
                        onRetry = { viewModel.retryLoading() }
                    )
                }
                is FinancialItemDetailsUIState.Success -> {
                    // Блок с названием вклада и суммой
                    FinancialItemInfoCard(state.data)

                    // Кнопки действий
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 34.dp, end = 34.dp, top = 16.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FinancialItemActionButton(
                            text = addMoneyText,
                            onClick = onAddMoneyClick,
                            modifier = Modifier.weight(1f)
                        )
                        FinancialItemActionButton(
                            text = closeItemText,
                            onClick = onCloseItemClick,
                            modifier = Modifier.weight(1f))
                    }

                    if (uiState.value is Deposit) {
                        /* операции по вкладу */
                        FinancialItemActionRow(
                            iconId = R.drawable.icon_operations,
                            text = stringResource(R.string.deposit_operations),
                            onClick = {})

                        /* начисленные проценты */
                        FinancialItemActionRow(
                            iconId = R.drawable.icon_percent,
                            text = stringResource(R.string.deposit_percent_list),
                            onClick = {})
                    } else {
                        /* график платежей */
                        FinancialItemActionRow(
                            iconId = R.drawable.credit_graph_icon,
                            text = stringResource(R.string.credit_graph),
                            onClick = {})

                        /* необходимо внести */
                        FinancialItemActionRow(
                            iconId = R.drawable.icon_percent,
                            text = stringResource(R.string.credit_operations),
                            onClick = {})
                    }
                }
            }
        }
    }
}