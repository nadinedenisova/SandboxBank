package com.example.sandboxbank.cardmanager.cards.debit.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.DebitCardUiState
import com.example.sandboxbank.R
import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items.NoInternetDialog
import com.example.sandboxbank.App.ui.designkit.mode.color.ColorSingleton

import com.example.sandboxbank.App.ui.designkit.mode.roboto
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.CardDescriptionItem
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.CardView
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.SuccessCardDialog

@Composable
fun DebitCardScreen(
    uiState: DebitCardUiState,
    onCreateCardClick: () -> Unit,
    onBackClick: () -> Unit,
    onDismissSuccessDialog: () -> Unit,
    onClearError: () -> Unit
) {
    var showSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.isCardCreated) {
        if (uiState.isCardCreated) {
            showSuccessDialog = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Top Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color(0xFFF3EDF7))
                    .padding(horizontal = 16.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_back_arrow_16x16),
                        contentDescription = null
                    )
                }
                Text(
                    text = stringResource(id = R.string.cards),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                CardView()

                Text(
                    text = stringResource(id = R.string.virtual_card_bank_debit),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 21.5.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                CardDescriptionItem(
                    stringResource(id = R.string.release_and_service),
                    stringResource(id = R.string.free)
                )
                CardDescriptionItem(
                    stringResource(id = R.string.cashback_30),
                    stringResource(id = R.string.cashback_30_value)
                )
                CardDescriptionItem(
                    "",
                    stringResource(id = R.string.pay_Qr)
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (uiState.isLimitReached) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.maximum_5_cards),
                        color = Color.Red,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = onCreateCardClick,
                    enabled = !uiState.isLimitReached,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (uiState.isLimitReached) Color.LightGray
                        else ColorSingleton.appPalette.value.primarySecond,
                        contentColor = ColorSingleton.appPalette.value.background
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.design_debit_card),
                        style = TextStyle(
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }

                if (uiState.isLoading) {
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }

    if (uiState.error == stringResource(R.string.no_internet)) {
        NoInternetDialog(
            onDismiss = onClearError,
            onRetry = {
                onClearError()
                onCreateCardClick()
            }
        )
    }

    if (showSuccessDialog) {
        SuccessCardDialog(onDismiss = {
            showSuccessDialog = false
            onDismissSuccessDialog()
        })
    }
}
