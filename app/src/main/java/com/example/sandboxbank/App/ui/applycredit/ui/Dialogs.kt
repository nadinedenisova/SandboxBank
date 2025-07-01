package com.example.sandboxbank.App.ui.applycredit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.designkit.mode.DarkColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.inter
import com.example.sandboxbank.App.ui.designkit.mode.roboto
import com.example.sandboxbank.App.ui.designkit.mode.selectColor
import com.example.sandboxbank.R

@Composable
fun CreditApproved(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            shape = RoundedCornerShape(28.dp),
            title = {
                Image(
                    contentDescription = null,
                    painter = painterResource(R.drawable.check_mark),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(
                    text = "Кредит\nоформлен",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = roboto,
                    fontWeight = FontWeight.W400,
                    fontSize = 24.sp,
                    color = selectColor(
                        LightColorPalette.onSurface,
                        DarkColorPalette.onSurface1
                    )
                )
            },
            confirmButton = {
                Text(
                    text = "Закрыть",
                    color = selectColor(
                        LightColorPalette.primaryInverce,
                        DarkColorPalette.primaryInverse
                    ),
                    modifier = Modifier
                        .clickable {
                            onConfirmation()
                        }
                        .padding(end = 20.dp, bottom = 26.dp))
            },

            )
    }
}

@Composable
fun CreditLimit(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            shape = RoundedCornerShape(28.dp),
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = "Превышен кредитный лимит:",
                        fontFamily = inter,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth()
                    )
                    Icon(
                        tint = LightColorPalette.primaryInverce,
                        contentDescription = null,
                        painter = painterResource(R.drawable.ic_error),
                        modifier = Modifier
                            .size(40.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                    )
                    Text(
                        text = "Можно оформить\nдо 3 кредитов, при этом сумма\nих задолженности должна быть\nменее 5 000 000,00",
                        fontFamily = inter,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Text(
                    text = "Закрыть",
                    color = selectColor(
                        LightColorPalette.primaryInverce,
                        DarkColorPalette.primaryInverse
                    ),
                    modifier = Modifier
                        .clickable {
                            onConfirmation()
                        }
                        .padding(end = 40.dp, bottom = 34.dp))
            },


            )
    }
}

@Composable
fun NoConnection(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            shape = RoundedCornerShape(28.dp),
            text = {
                Text(
                    text = "Нет подключения к интернету",
                    fontFamily = roboto,
                    fontWeight = FontWeight.W400,
                    fontSize = 21.sp,
                )
            },
            confirmButton = {
                Button(
                    onClick = onConfirmation,
                    modifier = Modifier.padding(end = 6.dp)
                ) {
                    Text(text = "Повторить попытку")
                }
            },
            dismissButton = {
                Text(
                    text = "Закрыть",
                    color = selectColor(
                        LightColorPalette.primaryInverce,
                        DarkColorPalette.primaryInverse
                    ),
                    modifier = Modifier
                        .clickable { onDismissRequest() }
                        .padding(top = 15.dp, end = 24.dp)
                )
            }

        )
    }
}

@Preview
@Composable
fun Approved() {
    CreditApproved(
        true, {}
    ) { }
}

@Preview
@Composable
fun Limit() {
    CreditLimit(
        true, {}
    ) { }
}

@Preview
@Composable
fun NoConn() {
    NoConnection(
        true, {}
    ) { }
}