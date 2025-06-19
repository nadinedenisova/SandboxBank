package com.example.sandboxbank.deposit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.core.deposit.data.db.FinancialType
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.R
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositScreen(deposit: Deposit) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
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
            // Блок с названием вклада и суммой
            DepositInfoCard(deposit)
            // Кнопки действий
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 34.dp, end = 34.dp, top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ActionButton(
                    text = stringResource(R.string.deposit_add_money),
                    onClick = { /* Пополнение */ },
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = stringResource(R.string.deposit_close),
                    onClick = { /* Закрытие вклада */ },
                    modifier = Modifier.weight(1f))
            }

            /* операции по вкладу */
            ActionRow(iconId = R.drawable.icon_operations, text = stringResource(R.string.deposit_operations), onClick = {})

            /* начисленные проценты */
            ActionRow(iconId = R.drawable.icon_percent, text = stringResource(R.string.deposit_percent_list), onClick = {})
        }
    }
}


@Composable
fun ActionRow(iconId: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 44.dp, vertical = 31.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEADDFF), // Цвет фона #EADDFF
            contentColor = Color(0xFF6750A4)    // Цвет текста #6750A4
        ),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp) // ← Меняем отступы

    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
        )
    }
}

@Composable
fun DepositInfoCard(deposit: Deposit) {
        Column(
            modifier = Modifier.padding(horizontal = 42.dp, vertical = 0.dp),
        ) {
            Text(
                text = deposit.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp
                ),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6750A4)
            )

            Text(
                text = deposit.balance.toFormattedBalance() + " " + stringResource(R.string.rouble_symbol),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 32.sp
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp, bottom = 0.dp)
            )

            Text(
                text = stringResource(R.string.interest_rate, deposit.percentRate),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp
                ),
                //color = MaterialTheme.colorScheme.onSurfaceVariant,
                color = Color(0xFF6750A4)
            )
        }
}

// Расширение для Double:
fun Double.toFormattedBalance(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale("ru", "RU")).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return numberFormat.format(this)
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
    ))
}