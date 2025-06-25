package com.example.sandboxbank.App.ui.creditcard.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.roundToInt


import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCreditSlider(
    min: Float,
    max: Float,
    step: Float = 100_000f,
    value: Float,
    onValueChange: (Float) -> Unit
) {

    val roboto = FontFamily.Default
    val stepsCount = ((max - min) / step).toInt() - 1
    val thumbPainter = painterResource(id = R.drawable.credit_stick_handle)

    val density = LocalDensity.current
    val sliderWidthPx = remember { mutableStateOf(0f) }
    val bubbleWidth = with(density) { 98.dp.toPx() }

    val percent = (value - min) / (max - min)
    val offsetX = (sliderWidthPx.value - bubbleWidth).coerceAtLeast(0f) * percent

    Column(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .size(width = 98.dp, height = 44.dp)
                    .background(Color(0xFF322F35), shape = RoundedCornerShape(100.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${value.toInt()} ₽",
                    color = Color(0xFFF5EFF7),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    sliderWidthPx.value = coordinates.size.width.toFloat()
                }
        ) {
            
            //TODO Пипец уже не знаю как делать, пушу в надежде =)
            Slider(
                value = value,
                onValueChange = {
                    val steppedValue = ((it - min) / step).roundToInt() * step + min
                    val coercedValue = steppedValue.coerceIn(min, max)
                    onValueChange(coercedValue)
                },
                valueRange = min..max,
                steps = stepsCount,
                thumb = {
                    Image(
                        painter = thumbPainter,
                        contentDescription = "Thumb",
                        modifier = Modifier.size(44.dp)
                    )
                }
                ,
                

                track = { sliderState ->
                    SliderDefaults.Track(
                        colors = SliderDefaults.colors(
                            activeTrackColor = Color(0xFF65558F),
                            inactiveTrackColor = Color(0xFFCAC4D0),
                            activeTickColor = Color(0xFFE8DEF8),
                            inactiveTickColor = Color(0xFF4A4459)
                        ),
                        sliderState = sliderState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .clip(RoundedCornerShape(50))
                    )
                },
                colors = SliderDefaults.colors(
                    thumbColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${min.toInt()} ₽",
                style = TextStyle(
                    fontFamily = roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = Color.Black
                )
            )

            Text(
                text = "${max.toInt()} ₽",
                style = TextStyle(
                    fontFamily = roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = Color.Black
                )
            )
        }
    }
}


