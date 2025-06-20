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
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.R
import com.example.sandboxbank.pinCode.PinCodeViewModel
import com.example.sandboxbank.pinCode.PinDotsType
import com.example.sandboxbank.pinCode.ui.intent.PinCodeIntent
import com.example.sandboxbank.pinCode.ui.model.AuthScreenUiState
import com.example.sandboxbank.viewModel

const val PIN_CODE_CORRECT_SIZE = 6

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
    onEvent: (PinCodeIntent) -> Unit,
    statePin: AuthScreenUiState,
) {
    KeyBoard(
        onEvent = onEvent,
        statePin = statePin,
    )
}

@Composable
fun PinDots(isFilled: Boolean, state: AuthScreenUiState) {
    val color = when (state.pinDotsType) {
        PinDotsType.DEFAULT -> if (isFilled) LightColorPalette.secondary else Color.Unspecified
        PinDotsType.INCORRECT -> LightColorPalette.onError
        PinDotsType.SUCCESS -> LightColorPalette.primaryInverce
    }

    Icon(
        painter = painterResource(R.drawable.ellipse_1),
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .padding(10.dp)
            .size(14.dp)
            .background(color = color, shape = RoundedCornerShape(25.dp))
    )
}

@Composable
fun KeyBoard(
    onEvent: (PinCodeIntent) -> Unit,
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = statePin.titleText,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = LightColorPalette.primaryFixedVariant
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(PIN_CODE_CORRECT_SIZE) { index ->
                val isFilled = index < statePin.pinCodeValue.length
                PinDots(isFilled = isFilled, statePin)
            }
        }
        listKeys.forEach { rows ->
            Row {
                rows.forEach {
                    KeyButton(
                        onClick = {
                            when (it) {
                                "del" -> if (statePin.pinCodeValue.isNotEmpty()) onEvent(
                                    PinCodeIntent.DeleteDigit
                                )

                                else -> {
                                    onEvent(PinCodeIntent.AddDigit(it))
                                }
                            }
                        }
                    ) {
                        when (it) {
                            "del" -> {
                                Icon(
                                    painter = painterResource(R.drawable.image_delete),
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
                color = LightColorPalette.secondaryContainer,
                shape = RoundedCornerShape(100)
            )
            .clickable(onClick = onClick)
            .defaultMinSize(minWidth = 75.dp, minHeight = 75.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

