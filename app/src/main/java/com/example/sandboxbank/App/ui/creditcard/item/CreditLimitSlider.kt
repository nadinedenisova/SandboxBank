package com.example.sandboxbank.App.ui.creditcard.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.Slider
import kotlin.math.roundToInt
import androidx.compose.material.Text
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@Composable
fun CustomCreditSlider(
    min: Float,
    max: Float,
    default: Float,
    step: Float,
    onValueChange: (Float) -> Unit
) {
    val context = LocalContext.current
    val roboto = FontFamily.Default
    var sliderValue by remember { mutableStateOf(default) }

    val density = LocalDensity.current
    val bubbleWidth = with(density) { 98.dp.toPx() }
    val sliderWidth = remember { mutableStateOf(1f) }

    val percent = (sliderValue - min) / (max - min)
    val offsetX = (sliderWidth.value - bubbleWidth).coerceAtLeast(0f) * percent

    Column(modifier = Modifier.fillMaxWidth()) {

        // Custom bubble
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
                    text = "${formatCurrency(sliderValue)} ₽",
                    color = Color(0xFFF5EFF7),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // AndroidView slider
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .onGloballyPositioned {
                    sliderWidth.value = it.size.width.toFloat()
                },
            factory = {
                Slider(context).apply {
                    valueFrom = min
                    valueTo = max
                    stepSize = step
                    value = default

                    // Отключаем системное облачко
                    labelBehavior = LabelFormatter.LABEL_GONE

                    addOnChangeListener { _, newValue, _ ->
                        sliderValue = newValue
                        onValueChange(newValue)
                    }
                }
            },
            update = { view ->
                if (view.value != sliderValue) {
                    view.value = sliderValue
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Min / Max labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${formatCurrency(min)} ₽",
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
                text = "${formatCurrency(max)} ₽",
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

private fun formatCurrency(value: Float): String {
    val format = DecimalFormat("#,###", DecimalFormatSymbols(Locale("ru")))
    return format.format(value.toInt())
}