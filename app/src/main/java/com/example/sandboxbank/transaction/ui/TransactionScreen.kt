package com.example.sandboxbank.transaction.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sandboxbank.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.transaction.domain.TransactionViewModel
import com.example.sandboxbank.transaction.ui.model.TransactionState
import com.example.sandboxbank.transaction.ui.model.TransactionUiState

@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel,
    onBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    TransactionScreenContent(
        uiState = uiState,
        onTabSelected = viewModel::onTabSelected,
        onDebitFromSelected = viewModel::onDebitFromSelected,
        onDebitToSelected = viewModel::onDebitToSelected,
        onAmountChanged = viewModel::onAmountChanged,
        onTransactionClick = viewModel::onTransactionClick,
        onDismissStatus = viewModel::resetStatus,
        onBackClick = onBack
    )
}

@Composable
fun TransactionScreenContent(
    uiState: TransactionUiState,
    onTabSelected: (Int) -> Unit,
    onDebitFromSelected: (String) -> Unit,
    onDebitToSelected: (String) -> Unit,
    onAmountChanged: (String) -> Unit,
    onTransactionClick: () -> Unit,
    onDismissStatus: () -> Unit,
    onBackClick: () -> Unit
) {
    val tabTitles = listOf("Между своими счетами", "Другому пользователю", "Прочие пополнения")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TopBar(onBackClick = onBackClick)

        TransactionTabs(
            tabs = tabTitles,
            selectedTabIndex = uiState.selectedTab,
            onTabSelected = onTabSelected
        )

        Spacer(Modifier.height(16.dp))

        Column(
            Modifier.background(LightColorPalette.background)
        ) {
            AccountDropdown(
                label = stringResource(R.string.transaction_deposit_out),
                selected = uiState.debitFrom,
                options = uiState.accounts,
                onSelect = onDebitFromSelected
            )

            Spacer(Modifier.height(8.dp))

            AccountDropdown(
                label = stringResource(R.string.transaction_deposit_in),
                selected = uiState.debitTo,
                options = uiState.accounts,
                onSelect = onDebitToSelected
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.amount,
                onValueChange = onAmountChanged,
                label = { Text(stringResource(R.string.transaction_sum)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Button(
                modifier = Modifier.align(Alignment.End),
                enabled = uiState.status != TransactionState.Loading,
                onClick = onTransactionClick,
                colors = ButtonDefaults.buttonColors(LightColorPalette.primary),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (uiState.status == TransactionState.Loading) {
                    CircularProgressIndicator(Modifier.size(20.dp), strokeWidth = 2.dp)
                } else {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = stringResource(R.string.transaction_button),
                        color = LightColorPalette.background,
                    )
                }
            }
        }
    }

    TransferStatusDialog(uiState.status, onDismiss = onDismissStatus)
}

@Composable
fun TopBar(
    onBackClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                androidx.compose.material.Text(
                    stringResource(R.string.transaction), style = TextStyle(
                        color = LightColorPalette.onSurface,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(
                            Font(R.font.roboto, weight = FontWeight.W400)
                        )
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painterResource(R.drawable.icon_back_arrow_16x16),
                        contentDescription = "Back"
                    )
                }
            },

            backgroundColor = LightColorPalette.surface,
            elevation = 0.dp
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountDropdown(
    label: String,
    selected: String,
    options: List<String>,
    onSelect: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = LightColorPalette.background,
                textColor = LightColorPalette.onSurface,
                focusedIndicatorColor = LightColorPalette.primary,
                unfocusedIndicatorColor = LightColorPalette.onSurface.copy(alpha = 0.5f)
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                ) {
                    Text(option)
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
                        is TransactionState.Success -> stringResource(R.string.transaction_done)
                        is TransactionState.NoInternet -> stringResource(R.string.transaction_internet)
                        is TransactionState.Loading -> stringResource(R.string.transaction_progress)
                        is TransactionState.Error -> stringResource(R.string.transaction_error)
                    }
                )
            },
            text = {
                when (status) {
                    is TransactionState.Error -> Text(status.message)
                    is TransactionState.NoInternet -> Text(stringResource(R.string.transaction_check_internet))
                    else -> {}
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.transaction_close_button))
                }
            }
        )
    }
}

@Composable
fun TransactionTabs(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    ScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = selectedTabIndex,
        contentColor = LightColorPalette.primary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = LightColorPalette.primary
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        title,
                        color = if (selectedTabIndex == index) LightColorPalette.primary else LightColorPalette.onSurface
                    )
                }
            )
        }
    }
}
