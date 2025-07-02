package com.example.sandboxbank.App.ui.financialitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import com.example.sandboxbank.App.ui.designkit.mode.color.ColorSingleton
import com.example.sandboxbank.R
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FinancialItemInfoCard(financialItem: FinancialItem) {
    Column(
        modifier = Modifier.padding(start = 42.dp, end = 42.dp, top = 32.dp, bottom = 0.dp),
    ) {
        Text(
            text = financialItem.name,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp
            ),
            fontWeight = FontWeight.Bold,
            color = ColorSingleton.appPalette.value.primaryInverce
        )

        Text(
            text = financialItem.balance.toFormattedBalance() + " " + stringResource(R.string.rouble_symbol),
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 32.sp
            ),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp, bottom = 0.dp),
            color = ColorSingleton.appPalette.value.onSecondary
        )

        Text(
            text = stringResource(R.string.interest_rate, financialItem.percentRate),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp
            ),
            color = ColorSingleton.appPalette.value.primaryInverce,
        )
    }
}

fun BigDecimal.toFormattedBalance(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale("ru", "RU")).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return numberFormat.format(this)
}