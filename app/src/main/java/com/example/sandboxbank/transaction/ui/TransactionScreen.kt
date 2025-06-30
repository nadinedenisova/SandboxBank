package com.example.sandboxbank.transaction.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sandboxbank.transaction.domain.TransactionViewModel
import com.example.sandboxbank.transaction.ui.model.TransactionState
import com.example.sandboxbank.transaction.ui.model.TransactionUiState
import com.example.sandboxbank.viewModel

@Composable
fun TransferScreen() {
    val viewModel: TransactionViewModel = viewModel()

    val uiState by viewModel.uiState.collectAsState()

    TransferScreenContent(
        uiState = uiState,
        onTabSelected = viewModel::onTabSelected,
        onDebitFromSelected = viewModel::onDebitFromSelected,
        onDebitToSelected = viewModel::onDebitToSelected,
        onAmountChanged = viewModel::onAmountChanged,
        onTransferClick = viewModel::onTransferClick,
        onDismissStatus = viewModel::resetStatus
    )
}

@Composable
fun TransferScreenContent(
    uiState: TransactionUiState,
    onTabSelected: (Int) -> Unit,
    onDebitFromSelected: (String) -> Unit,
    onDebitToSelected: (String) -> Unit,
    onAmountChanged: (String) -> Unit,
    onTransferClick: () -> Unit,
    onDismissStatus: () -> Unit
) {
    val tabTitles = listOf("Между своими счетами", "Другому пользователю", "Прочие пополнения")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TabRow(selectedTabIndex = uiState.selectedTab) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.selectedTab == index,
                    onClick = { onTabSelected(index) }
                ) {
                    Text(title, modifier = Modifier.padding(8.dp))
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        AccountDropdown(
            label = "Счёт списания",
            selected = uiState.debitFrom,
            options = uiState.accounts,
            onSelect = onDebitFromSelected
        )

        Spacer(Modifier.height(8.dp))

        AccountDropdown(
            label = "Счёт пополнения",
            selected = uiState.debitTo,
            options = uiState.accounts,
            onSelect = onDebitToSelected
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.amount,
            onValueChange = onAmountChanged,
            label = { Text("Сумма") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onTransferClick,
            enabled = uiState.status != TransactionState.Loading,
            modifier = Modifier.align(Alignment.End)
        ) {
            if (uiState.status == TransactionState.Loading) {
                CircularProgressIndicator(Modifier.size(20.dp), strokeWidth = 2.dp)
            } else {
                Text("Перевести")
            }
        }
    }

    TransferStatusDialog(uiState.status, onDismiss = onDismissStatus)
}

@Composable
fun AccountDropdown(
    label: String,
    selected: String,
    options: List<String>,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label)
        Box {
            OutlinedTextField(
                value = selected,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach {
                    DropdownMenuItem(onClick = {
                        onSelect(it)
                        expanded = false
                    }) {
                        Text(it)
                    }
                }
            }
        }
    }
}


@Composable
fun TransferStatusDialog(status: TransactionState?, onDismiss: () -> Unit) {
    if (status != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    when (status) {
                        is TransactionState.Success -> "Перевод выполнен"
                        is TransactionState.NoInternet -> "Нет подключения к интернету"
                        is TransactionState.Loading -> "Выполняется..."
                        is TransactionState.Error -> "Ошибка"
                    }
                )
            },
            text = {
                when (status) {
                    is TransactionState.Error -> Text(status.message)
                    is TransactionState.NoInternet -> Text("Проверьте подключение и попробуйте снова.")
                    else -> {}
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Закрыть")
                }
            }
        )
    }
}
