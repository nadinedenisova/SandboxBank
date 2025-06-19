package com.example.sandboxbank.loan.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.R
import androidx.compose.ui.text.TextStyle

@Composable
fun LoanDetailsScreen() {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.loan_title), style = TextStyle(
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightColorPalette.background)
                .padding(40.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "Ремонт",
                        color = LightColorPalette.primary,
                        fontWeight = FontWeight.Medium
                    )
                    IconButton(onClick = { /* handle back */ }) {
                        Icon(
                            painterResource(R.drawable.ic_loan_edit),
                            contentDescription = "Редактировать",
                            tint = LightColorPalette.primary
                        )
                    }
                }


                Text(
                    text = "1 000 000,00 ₽",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "Ставка 25% годовых",
                    style = TextStyle(
                        color = LightColorPalette.primary,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(R.font.roboto, weight = FontWeight.W400)
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(9.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { /* Handle payment */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEADDFF)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = stringResource(R.string.loan_payment),
                        color = LightColorPalette.primary,
                    )
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { /* Handle close */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEADDFF)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = stringResource(R.string.loan_close),
                        color = LightColorPalette.primary,
                    )
                }
            }

            Spacer(modifier = Modifier.height(56.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { /* Handle schedule */ }
            ) {
                Icon(
                    painterResource(R.drawable.ic_loan_schedule),
                    contentDescription = stringResource(R.string.loan_schedule),
                    tint = LightColorPalette.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stringResource(R.string.loan_schedule),
                    color = Color(0xFF6A4FAB),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { /* Handle required payment */ }
            ) {
                Icon(
                    painterResource(R.drawable.ic_loan_required),
                    contentDescription = stringResource(R.string.loan_required),
                    tint = LightColorPalette.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stringResource(R.string.loan_required),
                    color = Color(0xFF6A4FAB),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

    }

}
