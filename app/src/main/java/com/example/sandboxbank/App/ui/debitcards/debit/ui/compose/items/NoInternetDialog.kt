package com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.roboto
import com.example.sandboxbank.R

@Composable
fun NoInternetDialog(
    onDismiss: () -> Unit,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = Color.White,
            elevation = 8.dp,
            modifier = Modifier
                .width(312.dp)
                .height(192.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.no_internet),
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.closed_notification),
                            color = Color(0xFF6750A4), //TODO когда будет тема, переработать
                            fontSize = 14.sp
                        )
                    }


                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = onRetry,
                        modifier = Modifier
                            .height(48.dp)
                            .wrapContentWidth(),
                        shape = RoundedCornerShape(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = LightColorPalette.primary2,
                            contentColor = LightColorPalette.background
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.try_again_refresh),
                            fontFamily = roboto,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = LightColorPalette.background
                        )
                    }


                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoInternetDialogPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5)),
            contentAlignment = Alignment.Center
        ) {
            NoInternetDialog(
                onDismiss = {},
                onRetry = {}
            )
        }
    }
}