package com.example.sandboxbank.history.ui.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sandboxbank.App.ui.designkit.mode.ColorSingleton
import com.example.sandboxbank.R
import com.example.sandboxbank.auth.ui.screen.CustomTopBar
import com.example.sandboxbank.history.ui.state.HistoryViewState

@Preview(showSystemUi = true)
@Composable
fun OperationsHistoryScreen() {
    val state: HistoryViewState = HistoryViewState.Preview

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((ColorSingleton.appPalette.background))
    ) {
        CustomTopBar(stringResource(R.string.operations_history)) { TODO() }




        when(state) {
            HistoryViewState.Preview -> Preview()
            HistoryViewState.DebitCards -> OperationsHistory(stringResource(R.string.debit_cards))
            HistoryViewState.CreditCards -> OperationsHistory(stringResource(R.string.credit_cards))
            HistoryViewState.CurrentLoan -> OperationsHistory(stringResource(R.string.current_loans))
            HistoryViewState.CurrentDeposits -> OperationsHistory(stringResource(R.string.current_deposits))
            HistoryViewState.Transfers -> OperationsHistory(stringResource(R.string.transfers))
        }
    }
}



@Composable
fun OperationsHistory(
    title: String
) {

}

@Composable
fun Preview(
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Green,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Item(stringResource(R.string.debit_cards), TODO())
            Item(stringResource(R.string.credit_cards), TODO())
            Item(stringResource(R.string.current_deposits), TODO())
            Item(stringResource(R.string.current_loans), TODO())
            Item(stringResource(R.string.transfers), TODO())
        }
    }
}

@Composable
fun Item(
    operation: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = operation,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            textAlign = TextAlign.End
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_on_button),
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}