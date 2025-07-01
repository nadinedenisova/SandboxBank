package com.example.sandboxbank.App.ui.applycredit.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.example.sandboxbank.App.ui.applycredit.entity.ApplyCreditEntity
import com.example.sandboxbank.App.ui.applycredit.entity.ApplyCreditResponse
import com.example.sandboxbank.App.ui.applycredit.sendMockRequest
import com.example.sandboxbank.App.ui.designkit.mode.DarkColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.inter
import com.example.sandboxbank.App.ui.designkit.mode.roboto
import com.example.sandboxbank.App.ui.designkit.mode.selectColor
import com.example.sandboxbank.R
import kotlin.math.pow

const val CREDIT_PERCENT = 25
const val CREDIT_MAX_SUM = 3_000_000
const val CREDIT_MIN_SUM = 30_000
const val SLIDER_STEPS = 11

@Composable
fun ApplyCredit(
    onBackClick: () -> Unit,
) {

    val creditDates = listOf(3, 6, 9, 12, 15, 18, 21, 24)
    val creditSumLimits = getCreditLimit()

    val dialogApprovedIsVisible = remember { mutableStateOf(false) }
    val dialogLimitIsVisible = remember { mutableStateOf(false) }
    val dialogConnectionIsVisible = remember { mutableStateOf(false) }

    var sliderDateValue by remember {
        mutableFloatStateOf(creditDates[creditDates.size / 2 - 1].toFloat())
    }
    var sliderSumValue by remember {
        mutableFloatStateOf(creditSumLimits[creditSumLimits.size / 2 - 1].toFloat())
    }


    Column {
        CreditApproved(
            visible = dialogApprovedIsVisible.value,
            onDismissRequest = { dialogApprovedIsVisible.value = false },
            onConfirmation = { dialogApprovedIsVisible.value = false }
        )

        CreditLimit(
            visible = dialogLimitIsVisible.value,
            onConfirmation = {
                dialogLimitIsVisible.value = false

                             },
            onDismissRequest = { dialogLimitIsVisible.value = false },
        )

        NoConnection(
            visible = dialogConnectionIsVisible.value,
            onConfirmation = { dialogConnectionIsVisible.value = false },
            onDismissRequest = { dialogConnectionIsVisible.value = false },
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(selectColor(LightColorPalette.surface, DarkColorPalette.onSurface2))
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back_arrow_16x16),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .padding(horizontal = 20.dp)
                    .clickable {
                        onBackClick()
                    },
                tint = selectColor(
                    LightColorPalette.onSurface,
                    DarkColorPalette.onSurfaceContainerHighest
                ),
            )
            Text(
                text = stringResource(R.string.apply_credit_head),
                fontFamily = roboto,
                fontWeight = FontWeight.W400,
                fontSize = 22.sp,
                color = selectColor(
                    LightColorPalette.onSurface,
                    DarkColorPalette.onSurfaceContainerHighest
                )
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
                        color = selectColor(
                            LightColorPalette.onSurface,
                            DarkColorPalette.onSurfaceContainerHighest
                        )
                    )
                }

                Box(
                    modifier = Modifier

                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = selectColor(
                                LightColorPalette.outlineVariant,
                                DarkColorPalette.onSurfaceVariant2
                            ),
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "${stringResource(R.string.apply_credit_rate_head)} " +
                                "\n${CREDIT_PERCENT}% " +
                                stringResource(R.string.apply_credit_rate_last),
                        fontFamily = roboto,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = selectColor(
                            LightColorPalette.onSurface,
                            DarkColorPalette.onSurfaceContainerHighest
                        )
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(R.string.apply_credit_select_limit),
                    fontFamily = inter,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = selectColor(
                        LightColorPalette.onSurface,
                        DarkColorPalette.onSurfaceContainerHighest
                    )
                )

                ViewSlider(
                    values = creditSumLimits,
                    type = stringResource(R.string.deposit_sum_symbol),
                    sliderValue = sliderSumValue,
                    onSliderChange = { newValue ->
                        sliderSumValue = newValue
                    }
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(R.string.apply_credit_select_trime_limit),
                    fontFamily = inter,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = selectColor(
                        LightColorPalette.onSurface,
                        DarkColorPalette.onSurfaceContainerHighest
                    )
                )


                ViewSlider(
                    values = creditDates,
                    type = stringResource(R.string.apply_credit_month),
                    sliderValue = sliderDateValue,
                    onSliderChange = { newValue ->
                        sliderDateValue = newValue
                    }
                )

                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = selectColor(
                                LightColorPalette.outlineVariant,
                                DarkColorPalette.onSurfaceVariant2
                            )
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                        text = stringResource(R.string.apply_credit_monthly_payment),
                        fontFamily = roboto,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = selectColor(
                            LightColorPalette.onSurface,
                            DarkColorPalette.onSurfaceContainerHighest
                        )
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .padding(horizontal = 16.dp),
                        text = "%.2f ${
                            stringResource(R.string.deposit_sum_symbol)
                        }".format(
                            calculateMonthlyPay(
                                sum = sliderSumValue,
                                date = sliderDateValue.toInt(),
                            )
                        ),
                        fontFamily = roboto,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = selectColor(
                            LightColorPalette.onSurface,
                            DarkColorPalette.onSurfaceContainerHighest
                        )
                    )
                }


                Button(
                    onClick = {
                        val result = sendMockRequest()

                        when (result) {
                            ApplyCreditResponse.CreditAmountLimit -> {
                                dialogLimitIsVisible.value = true

                            }
                            ApplyCreditResponse.NoConnection -> {
                                dialogConnectionIsVisible.value = true
                                val creditSaveState = ApplyCreditEntity(
                                    sum = sliderSumValue.toBigDecimal(),
                                    time = sliderDateValue.toInt(),
                                )
                            }
                            ApplyCreditResponse.Success -> {
                                dialogApprovedIsVisible.value = true
                                onBackClick()
                            }
                        }
                    },
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
                        color = selectColor(
                            LightColorPalette.background,
                            DarkColorPalette.onPrimary
                        )
                    )
                }

            }

        }
    }
}


fun getCreditLimit(): List<Int> {
    val step = (CREDIT_MAX_SUM - CREDIT_MIN_SUM) / SLIDER_STEPS

    val creditLimit = mutableListOf(CREDIT_MIN_SUM)

    for (i in 1..(SLIDER_STEPS)) {
        creditLimit.add(step + creditLimit[i - 1])
    }

    return creditLimit.toList()
}

fun calculateMonthlyPay(
    sum: Float,
    date: Int,
): Float {

    val monthlyRate = (CREDIT_PERCENT.toDouble() / 100) / 12
    val denominator = 1 - (1 + monthlyRate).pow(-date.toDouble())

    return ((sum * monthlyRate) / denominator).toFloat()
}

//fun formatNumber(number: Float): String{
//    val formatter = DecimalFormat("#,###.##")
//    return formatter.format(number)
//}

@Preview(
    showSystemUi = false, showBackground = false, device = "id:pixel_5", locale = "ru",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewApplyCredit() {
    ApplyCredit(
        onBackClick = {}
    )
}
