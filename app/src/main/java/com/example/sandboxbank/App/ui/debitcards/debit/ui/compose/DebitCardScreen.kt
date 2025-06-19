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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items.NoInternetDialog
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.roboto
import com.example.sandboxbank.R
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardState
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.CardDescriptionItem
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.CardView
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.SuccessCardDialog

@Composable
fun DebitCardScreen(
    state: CardState,
    onCreateCardClick: () -> Unit,
    onBackClick: () -> Unit,
    onDismissSuccessDialog: () -> Unit,
    onClearError: () -> Unit
) {
    var showSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(state.card) {
        if (state.card != null) {
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

            // Scrollable Content
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

                if (state.isLimitReached) {
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
                    enabled = !state.isLimitReached,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (state.isLimitReached) Color.LightGray
                        else LightColorPalette.primary2,
                        contentColor = LightColorPalette.background
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

                if (state.isLoading) {
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

            }
        }
    }
    if (state.error == stringResource(R.string.no_internet)) {
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


@Preview(showBackground = true)
@Composable
fun DebitCardScreenPreview() {
    DebitCardScreen(
        state = CardState(
            isLoading = false,
            isLimitReached = false,
            error = null
        ),
        onCreateCardClick = {},
        onBackClick = {},
        onDismissSuccessDialog = {},
        onClearError = {}
    )
}
