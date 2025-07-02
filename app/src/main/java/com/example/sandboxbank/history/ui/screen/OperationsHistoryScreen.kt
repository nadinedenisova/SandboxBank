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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.MaterialTheme
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
import com.example.sandboxbank.App.ui.designkit.mode.ColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.ColorSingleton
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.R
import com.example.sandboxbank.auth.ui.screen.CustomTopBar
import com.example.sandboxbank.history.domain.model.OperationItems
import com.example.sandboxbank.history.ui.state.HistoryViewState

@Preview(showSystemUi = true)
@Composable
fun OperationsHistoryScreen() {
    val state: HistoryViewState = HistoryViewState.Preview
    val mokList = listOf<OperationItems>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((ColorSingleton.appPalette.background))
    ) {
        CustomTopBar(stringResource(R.string.operations_history)) { TODO() }

        Spacer(Modifier.height(16.dp))

        when(state) {
            HistoryViewState.Preview -> PreviewScreen()
            HistoryViewState.DebitCards -> OperationsHistory(stringResource(R.string.debit_cards), mokList, Modifier)
            HistoryViewState.CreditCards -> OperationsHistory(stringResource(R.string.credit_cards), mokList, Modifier)
            HistoryViewState.CurrentLoan -> OperationsHistory(stringResource(R.string.current_loans), mokList, Modifier)
            HistoryViewState.CurrentDeposits -> OperationsHistory(stringResource(R.string.current_deposits), mokList, Modifier)
            HistoryViewState.Transfers -> OperationsHistory(stringResource(R.string.transfers), mokList, Modifier)
        }
    }
}


@Composable
fun OperationsHistory(
    title: String,
    operations: List<OperationItems>,
    modifier: Modifier = Modifier
) {
    Text(title)

    LazyColumn(modifier = modifier) {
        operations.forEach { operation ->
            item {
                DatedItemSection(
                    date = operation.data,
                    items = operation.operation,
                )
            }
        }
    }
}

@Composable
private fun DatedItemSection(
    date: String,
    items: List<Unit>,
) {
    Column {
        Text(
            text = date,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Divider(
            modifier = Modifier.padding(top = 8.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )

        items.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {

            }
        }
    }
}

@Composable
fun PreviewScreen(
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = ColorSingleton.appPalette.secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ItemOperation(stringResource(R.string.debit_cards), {})
            ItemOperation(stringResource(R.string.credit_cards), {})
            ItemOperation(stringResource(R.string.current_deposits), {})
            ItemOperation(stringResource(R.string.current_loans), {})
            ItemOperation(stringResource(R.string.transfers), {})
        }
    }
}

@Composable
fun ItemOperation(
    operation: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = operation,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_on_button),
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}