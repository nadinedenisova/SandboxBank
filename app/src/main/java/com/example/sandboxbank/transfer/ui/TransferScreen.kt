package com.example.sandboxbank.transfer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sandboxbank.transfer.domain.TransferViewModel
import com.example.sandboxbank.viewModel

@Composable
fun TransferScreen() {
    val viewModel: TransferViewModel = viewModel()

    TransferScreenContent(viewModel = viewModel)
}

@Composable
fun TransferScreenContent(viewModel: TransferViewModel) {
    val tabTitles = listOf("Между своими счетами", "Другому пользователю", "Прочие пополнения")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TabRow(selectedTabIndex = viewModel.selectedTab) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = viewModel.selectedTab == index, onClick = {
                    viewModel.selectedTab = index
                }) {
                    Text(title, modifier = Modifier.padding(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AccountDropdown("Счёт списания", viewModel.debitFrom, viewModel.accounts) {
            viewModel.debitFrom = it
        }

        Spacer(modifier = Modifier.height(8.dp))

        AccountDropdown("Счёт пополнения", viewModel.debitTo, viewModel.accounts) {
            viewModel.debitTo = it
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.amount,
            onValueChange = { viewModel.amount = it },
            label = { Text("Сумма") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onTransferClick() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Перевести")
        }

        TransferStatusDialog(viewModel.status, onDismiss = { viewModel.resetStatus() })
    }
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
fun TransferStatusDialog(status: TransferViewModel.TransferStatus?, onDismiss: () -> Unit) {
    if (status != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(if (status is TransferViewModel.TransferStatus.NoInternet) "Повторить попытку" else "Закрыть")
                }
            },
            title = {
                Text(
                    when (status) {
                        TransferViewModel.TransferStatus.Success -> "Перевод выполнен"
                        TransferViewModel.TransferStatus.NoInternet -> "Нет подключения к интернету"
                        TransferViewModel.TransferStatus.Loading -> "Выполняется..."
                        null -> ""
                    }
                )
            },
            dismissButton = {
                if (status is TransferViewModel.TransferStatus.NoInternet) {
                    TextButton(onClick = onDismiss) {
                        Text("Закрыть")
                    }
                }
            }
        )
    }
}