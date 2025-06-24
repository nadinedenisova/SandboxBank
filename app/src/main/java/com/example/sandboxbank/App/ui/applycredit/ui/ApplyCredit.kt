package com.example.sandboxbank.App.ui.applycredit.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.sandboxbank.App.ui.designkit.mode.DarkColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.inter
import com.example.sandboxbank.App.ui.designkit.mode.roboto
import com.example.sandboxbank.App.ui.designkit.mode.selectColor
import com.example.sandboxbank.R
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.Slider


@Composable
fun ApplyCredit(
    percent: Float,//Процентная ставка
    minCreditValue: Float,//Минимальный размер кредита
    maxCreditValue: Float,//Максимальный размер кредита
    minMonth: Int,//Минимальный срок
    maxMounth: Int,//Максимальный срок
){

    var sliderCashLimit by remember { mutableFloatStateOf(0f) }
    var sliderDateLimit by remember { mutableFloatStateOf(0f) }


    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(selectColor(LightColorPalette.surface, DarkColorPalette.onSurface2))
                .fillMaxWidth()
        ){
            Icon(
                painter = painterResource(id = R.drawable.icon_back_arrow_16x16),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .padding(horizontal = 20.dp),
                tint = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
            )
            Text(
                text = stringResource(R.string.apply_credit_head),
                fontFamily = roboto,
                fontWeight = FontWeight.W400,
                fontSize = 22.sp,
                color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
            )
        }
        Box(
            modifier = Modifier.background(
                selectColor(
                    LightColorPalette.background,
                    DarkColorPalette.onSurfaceContainerLowest
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 17.dp, top = 14.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .fillMaxWidth()
                        .background(
                            selectColor(
                                LightColorPalette.surfaceContainerHigh,
                                DarkColorPalette.surfaceContainerHigh
                            )
                        )
                ) {
                    Text(
                        text = stringResource(R.string.apply_credit_conditions),
                        modifier = Modifier.padding(17.dp),
                        fontFamily = inter,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
                    )
                }

                Box (
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = selectColor(
                                LightColorPalette.outlineVariant,
                                DarkColorPalette.onSurfaceVariant
                            )
                        )
                        .fillMaxWidth()
                ){
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "${stringResource(R.string.apply_credit_rate_head)} " +
                                "\n25% " +
                                stringResource(R.string.apply_credit_rate_last),
                        fontFamily = roboto,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(R.string.apply_credit_select_limit),
                    fontFamily = inter,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
                )

                ViewSlider(
                    min = 30_000f,
                    max = 3_000_000f,
                    type = "Р"
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(R.string.apply_credit_select_trime_limit),
                    fontFamily = inter,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
                )


                ViewSlider(
                    min = 3f,
                    max = 24f,
                    type = "мес"
                )

                Column (
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = selectColor(
                                LightColorPalette.outlineVariant,
                                DarkColorPalette.onSurfaceVariant
                            )
                        )
                        .fillMaxWidth()
                ){
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                        text = stringResource(R.string.apply_credit_monthly_payment),
                        fontFamily = roboto,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .padding(horizontal = 16.dp),
                        text = "120 000,00 Р",
                        fontFamily = roboto,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
                    )
                }


                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),

                    ) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = "Оформить кредит",
                        fontFamily = roboto,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = selectColor(LightColorPalette.background, DarkColorPalette.onPrimary)
                    )
                }

            }

        }
    }


}

@Composable
fun ViewSlider(
    min: Float,
    max: Float,
    type: String,
){

    val step: Float = (max - min) / 10
    val default: Float = min + (step * 5)


    AndroidView(
        factory = { context ->
            Slider(context).apply {
                stepSize = step
                valueFrom = min
                valueTo = max
                labelBehavior = LabelFormatter.LABEL_VISIBLE
                value = default

            }
        },
        modifier = Modifier.padding(vertical = 8.dp)
    )

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "${min.toInt()} $type",
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
            )
        Text(
            text = "${max.toInt()} $type",
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            color = selectColor(LightColorPalette.onSurface, DarkColorPalette.onSurfaceContainerHighest)
            )
    }


}


@Preview(
    showSystemUi = false, showBackground = false, device = "id:pixel_5", locale = "ru",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewApplyCredit(){
    ApplyCredit(
        maxCreditValue = 300_000_000f,
        minCreditValue = 30_000f,
        maxMounth = 24,
        minMonth = 3,
        percent = 25f,
    )
}
