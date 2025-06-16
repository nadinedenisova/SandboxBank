package com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.sandboxbank.R

@Composable
fun SuccessCardDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        buttons = {},
        title = null,
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(312.dp)
                    .height(192.dp)
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_card_success),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.card_success_title),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.clossed), color = MaterialTheme.colors.primary)
                }
            }
        },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
}

@Preview(showBackground = true)
@Composable
fun SuccessCardDialogPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5)),
            contentAlignment = Alignment.Center
        ) {
            SuccessCardDialog(onDismiss = {})
        }
    }
}

