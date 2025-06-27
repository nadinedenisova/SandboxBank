package com.example.sandboxbank.App.ui.applycredit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sandboxbank.App.ui.designkit.mode.DarkColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
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
                    painter = painterResource(R.drawable.check_mark)
                )
                    },
            text = { Text(text = "Кредит оформлен") },
            confirmButton = {
                Text(
                    text =  "Закрыть",
                    modifier = Modifier.clickable {
                        onConfirmation()
                    })
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
                Column {
                    Text(text = "Превышен кредитный лимит:")
                    Icon(
                        tint = LightColorPalette.primaryInverce,
                        contentDescription = null,
                        painter = painterResource(R.drawable.ic_error),
                        modifier = Modifier.size(40.dp),
                    )
                    Text(text = "Можно оформить до 3 кредитов, при этом сумма их задолженности должна быть менее 5 000 000,00")
                }
                },
            confirmButton = {
                Text(
                    text =  "Закрыть",
                    modifier = Modifier.clickable {
                        onConfirmation()
                    })
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
            text = { Text(text = "Нет подключения к интернету") },
            confirmButton = {
                Button(
                    onClick = onConfirmation
                ){
                    Text(text = "Повторить попытку",)
                }
            },
            dismissButton = {
                Text(text = "Закрыть",
                    modifier = Modifier.clickable { onDismissRequest()  })
            }

        )
    }
}

@Preview
@Composable
fun Approved(){
    CreditApproved(
        true, {}
    ) { }
}

@Preview
@Composable
fun Limit(){
    CreditLimit(
        true, {}
    ) { }
}

@Preview
@Composable
fun NoConn(){
    NoConnection(
        true, {}
    ) { }
}