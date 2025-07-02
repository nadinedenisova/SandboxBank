package com.example.sandboxbank.App.ui.applycredit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.sandboxbank.App.ui.designkit.mode.DarkColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.roboto
import com.example.sandboxbank.App.ui.designkit.mode.selectColor
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.Slider

@Composable
fun ViewSlider(
    values: List<Int>,
    type: String,
    sliderValue: Float,
    onSliderChange: (Float) -> Unit
) {

    AndroidView(
        factory = { context ->
            Slider(context).apply {
                stepSize = (values[1] - values[0]).toFloat()
                valueFrom = values.first().toFloat()
                valueTo = values.last().toFloat()
                labelBehavior = LabelFormatter.LABEL_VISIBLE
                value = sliderValue

            }
        },
        update = { view ->
            view.addOnChangeListener { slider, value, fromUser -> onSliderChange(value) }

        },
        modifier = Modifier.padding(vertical = 8.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${values.first()} $type",
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            color = selectColor(
                LightColorPalette.onSurface,
                DarkColorPalette.onSurfaceContainerHighest
            )
        )
        Text(
            text = "${values.last()} $type",
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            color = selectColor(
                LightColorPalette.onSurface,
                DarkColorPalette.onSurfaceContainerHighest
            )
        )
    }


}