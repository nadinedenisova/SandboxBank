package com.example.sandboxbank.pinCode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.pinCode.PinCodeViewModel
import com.example.sandboxbank.viewModel

@Composable
fun PinCodeScreen(

) {
    val viewModel: PinCodeViewModel = viewModel()
    val authScreenState by viewModel.authScreenStateFlow.collectAsState()

    PinCodeScreenContent(
        onEvent = viewModel::onEvent,
        statePin = viewModel.authScreenUiStateMapper.map(authScreenState)
    )
}

@Composable
fun PinCodeScreenContent(
    onEvent: (PinLockEvent) -> Unit,
    statePin: AuthScreenUiState,
) {
    KeyBoard(
        onEvent = onEvent,
        statePin = statePin,
    )
}

@Composable
fun PinDots(isFilled: Boolean) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(30.dp)
            .background(
                if (isFilled) Color(0xFF3E206E) else Color.Gray
            )
    )
}

@Composable
fun KeyBoard(
    onEvent: (PinLockEvent) -> Unit,
    statePin: AuthScreenUiState
) {
    val listKeys = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("finger print", "0", "del")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = statePin.titleText,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(6) { index ->
                val isFilled = index < statePin.pinCodeValue.length
                PinDots(isFilled = isFilled)
            }
        }
        listKeys.forEach { rows ->
            Row {
                rows.forEach {
                    KeyButton(
                        onClick = {
                            when (it) {
                                "del" -> if (statePin.pinCodeValue.isNotEmpty()) onEvent(
                                    PinLockEvent.DeleteDigit
                                )

                                else -> {
                                    onEvent(PinLockEvent.AddDigit(it))
                                    if (statePin.pinCodeValue.length == 5) {
                                        onEvent(PinLockEvent.CheckPin)
                                    }
                                }
                            }
                        }
                    ) {
                        when (it) {
                            "del" -> {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Clear",
                                    modifier = Modifier
                                        .size(30.dp),
                                )
                            }

                            "finger print" -> {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Success",
                                    modifier = Modifier
                                        .size(35.dp),
                                )
                            }

                            else -> Text(
                                text = it,
                                fontSize = 30.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun KeyButton(
    onClick: () -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(100),
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(shape)
            .background(
                color = Color.Blue,
                shape = RoundedCornerShape(100)
            )
            .clickable(onClick = onClick)
            .defaultMinSize(minWidth = 95.dp, minHeight = 95.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}


data class AuthScreenUiState(
    val pinCodeValue: String = "",
    val titleText: String,
)

sealed interface PinLockEvent {
    data class AddDigit(val digit: String) : PinLockEvent
    data object DeleteDigit : PinLockEvent
    data object CheckPin : PinLockEvent
}

