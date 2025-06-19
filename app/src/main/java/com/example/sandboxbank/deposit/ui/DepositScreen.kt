package com.example.sandboxbank.deposit.ui

import androidx.compose.foundation.background
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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.R

@Composable
fun DepositScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.increase_deposit), style = TextStyle(
                            color = LightColorPalette.onSurface,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.roboto, weight = FontWeight.W400)
                            )
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* handle back */ }) {
                        Icon(painterResource(R.drawable.ic_arrow_back), contentDescription = "Back")
                    }
                },

                backgroundColor = LightColorPalette.surface,
                elevation = 0.dp
            )
        }
        Column(
            modifier = Modifier
                .background(Color(0xFFFDF9FC))
                .padding(16.dp)
        ) {


            Spacer(modifier = Modifier.height(16.dp))

            // Счёт списания
            AccountCard(
                title = stringResource(R.string.outcome_deposit),
                amount = "50 000,00 ₽",
                subtitle = "Дебетовая карта"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(R.drawable.ic_arrow_deposit),
                    contentDescription = "Swap",
                )
            }

            // Счёт пополнения
            AccountCard(
                title = stringResource(R.string.increase_deposit),
                amount = "0 ₽",
                subtitle = "Вклад (Счастливое будущее)"
            )

            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { /* handle input */ },
                label = { Text(stringResource(R.string.sum_deposit)) },
                leadingIcon = { Text(stringResource(R.string.sum_symbol_deposit)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* handle deposit */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LightColorPalette.primary)
            ) {
                Text(stringResource(R.string.increase_button_deposit), color = LightColorPalette.onPrimary)
            }
        }
    }


}

@Composable
fun AccountCard(title: String, amount: String, subtitle: String) {
    Card(
        elevation = 1.dp,
        backgroundColor = LightColorPalette.surface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
            Text(
                title,
                style = TextStyle(
                    color = LightColorPalette.onSecondary,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.roboto, weight = FontWeight.W600)
                    )
                ),
                color = LightColorPalette.onSecondary
            )
            Text(
                amount,
                style = TextStyle(
                    color = LightColorPalette.onSurface,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.roboto, weight = FontWeight.W600)
                    )
                ),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            Text(
                subtitle,
                style = TextStyle(
                    color = LightColorPalette.onSurface,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.roboto, weight = FontWeight.W400)
                    )
                ),
            )
        }
    }
}