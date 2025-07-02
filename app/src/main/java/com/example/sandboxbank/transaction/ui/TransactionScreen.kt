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
import com.example.sandboxbank.App.ui.designkit.mode.color.ColorSingleton
import com.example.sandboxbank.App.ui.designkit.mode.language.LanguageSingleton
import com.example.sandboxbank.transaction.domain.TransactionViewModel
import com.example.sandboxbank.transaction.ui.model.TransactionState
import com.example.sandboxbank.transaction.ui.model.TransactionUiState
import com.example.sandboxbank.App.ui.designkit.mode.language.*

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

@OptIn(ExperimentalMaterialApi::class)
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
    val tabTitles = listOf(LanguageSingleton.localization.value.betweenAccounts(), LanguageSingleton.localization.value.toAnotherUser(), LanguageSingleton.localization.value.otherDeposits())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(ColorSingleton.appPalette.value.background)
    ) {

        TopBar(onBackClick = onBackClick)

        TransactionTabs(
            tabs = tabTitles,
            selectedTabIndex = uiState.selectedTab,
            onTabSelected = onTabSelected
        )

        Spacer(Modifier.height(16.dp))

        Column(
            Modifier.background(ColorSingleton.appPalette.value.background)
        ) {
            AccountDropdown(
                label = LanguageSingleton.localization.value.debitAccount(),
                selected = uiState.debitFrom,
                options = uiState.accounts,
                onSelect = onDebitFromSelected
            )

            Spacer(Modifier.height(8.dp))

            AccountDropdown(
                label = LanguageSingleton.localization.value.replenishmentAccount(),
                selected = uiState.debitTo,
                options = uiState.accounts,
                onSelect = onDebitToSelected
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.amount,
                onValueChange = onAmountChanged,
                label = { Text(LanguageSingleton.localization.value.amount(), color =ColorSingleton.appPalette.value.onSurface ) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                        backgroundColor = ColorSingleton.appPalette.value.background,
                textColor = ColorSingleton.appPalette.value.onSurface,
                focusedIndicatorColor = ColorSingleton.appPalette.value.primary,
                unfocusedIndicatorColor = ColorSingleton.appPalette.value.onSurface.copy(alpha = 0.5f)
            )
            )

            Spacer(Modifier.height(16.dp))

            Button(
                modifier = Modifier.align(Alignment.End),
                enabled = uiState.status != TransactionState.Loading,
                onClick = onTransactionClick,
                colors = ButtonDefaults.buttonColors(ColorSingleton.appPalette.value.primary),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (uiState.status == TransactionState.Loading) {
                    CircularProgressIndicator(Modifier.size(20.dp), strokeWidth = 2.dp)
                } else {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = LanguageSingleton.localization.value.toTransfer(),
                        color = ColorSingleton.appPalette.value.background,
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
                    LanguageSingleton.localization.value.transfersTitle(), style = TextStyle(
                        color = ColorSingleton.appPalette.value.onSurface,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(
                            Font(R.font.roboto, weight = FontWeight.W400)
                        )
                    )
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        painterResource(R.drawable.icon_back_arrow_16x16),
                        contentDescription = "Back",
                        tint = ColorSingleton.appPalette.value.onSurface,
                    )
                }
            },
            backgroundColor = ColorSingleton.appPalette.value.surface,
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
            label = { Text(text = label, color =ColorSingleton.appPalette.value.onSurface) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = ColorSingleton.appPalette.value.background,
                textColor = ColorSingleton.appPalette.value.onSurface,
                focusedIndicatorColor = ColorSingleton.appPalette.value.primary,
                unfocusedIndicatorColor = ColorSingleton.appPalette.value.onSurface.copy(alpha = 0.5f)
            )
        )

        ExposedDropdownMenu(
            modifier = Modifier.background(ColorSingleton.appPalette.value.surface),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    modifier = Modifier.background(ColorSingleton.appPalette.value.surface),
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                ) {
                    Text(text = option, color = ColorSingleton.appPalette.value.onSurface)
                }
            }
        }
    }
}


@Composable
fun TransferStatusDialog(status: TransactionState?, onDismiss: () -> Unit) {
    if (status != null) {
        AlertDialog(
            backgroundColor = ColorSingleton.appPalette.value.surface,
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = when (status) {
                        is TransactionState.Success -> LanguageSingleton.localization.value.transferDone()
                        is TransactionState.NoInternet -> LanguageSingleton.localization.value.transferInternet()
                        is TransactionState.Loading -> LanguageSingleton.localization.value.transferProgress()
                        is TransactionState.Error -> LanguageSingleton.localization.value.transferError()
                    },
                    color =ColorSingleton.appPalette.value.onSurface
                )
            },
            text = {
                when (status) {
                    is TransactionState.Error -> Text(text = status.message, color = ColorSingleton.appPalette.value.onSurface)
                    is TransactionState.NoInternet -> Text(text = LanguageSingleton.localization.value.transferCheck(), color = ColorSingleton.appPalette.value.onSurface)
                    else -> {}
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = LanguageSingleton.localization.value.transferClose(), color =ColorSingleton.appPalette.value.onSurface)
                }
            },

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
        containerColor = ColorSingleton.appPalette.value.background,
        contentColor = ColorSingleton.appPalette.value.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = ColorSingleton.appPalette.value.primary
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
                        color = if (selectedTabIndex == index) ColorSingleton.appPalette.value.primary else ColorSingleton.appPalette.value.onSurface
                    )
                }
            )
        }
    }
}
