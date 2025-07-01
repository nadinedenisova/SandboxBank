package com.example.sandboxbank.App.ui.financialScreen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.designkit.mode.color.ColorSingleton
import com.example.sandboxbank.App.ui.designkit.mode.language.LanguageSingleton
import com.example.sandboxbank.App.ui.financialScreen.data.FinanceState
import com.example.sandboxbank.App.ui.financialScreen.domain.FinancialScreenViewModel
import com.example.sandboxbank.R
import com.example.sandboxbank.App.ui.designkit.mode.language.*

@Composable
fun FinancialScreenContent(
    viewModel: FinancialScreenViewModel,
    onDepositClick:(depositId: Long)-> Unit,
    onCreditClick:(creditId: Long)-> Unit,
    onApplyCreditClick:()-> Unit,
) {
    val stateDepos = viewModel.stateDeposFlow.collectAsState().value
    val stateCredits = viewModel.stateCreditsFlow.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top))
            .background(ColorSingleton.appPalette.value.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TitleContent(title = LanguageSingleton.localization.value.deposit())

        when(stateDepos) {
            is FinanceState.ContentDeposits -> {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    items(stateDepos.deposits.size) { it->
                        DepositItem(stateDepos.deposits[it], onClick = onDepositClick)
                    }
                }
            }

            is FinanceState.Error -> {}
            FinanceState.Loading -> {}
            else -> Unit
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorSingleton.appPalette.value.primary,
            ),
            shape = RoundedCornerShape(100.dp),
            onClick = {
            }
        ) {
            Text(
                text = LanguageSingleton.localization.value.depositOpen(),
                style = TextStyle(
                    color = ColorSingleton.appPalette.value.background,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(500)
                ),
            )
        }

        TitleContent(title = LanguageSingleton.localization.value.credits())

        when(stateCredits) {
            is FinanceState.ContentCredits -> {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    items(stateCredits.credits.size) { it->
                        CreditItem (stateCredits.credits[it], onClick = onCreditClick)
                    }
                }
            }

            is FinanceState.Error -> {}
            FinanceState.Loading -> {}
            else -> Unit
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =ColorSingleton.appPalette.value.primary,
            ),
            shape = RoundedCornerShape(100.dp),
            onClick = {
                onApplyCreditClick()
            }
        ) {
            Text(
                text = LanguageSingleton.localization.value.creditOpen(),
                style = TextStyle(
                    color = ColorSingleton.appPalette.value.background,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(500)
                ),
            )
        }
    }
}

@Composable
fun TitleContent(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, bottom = 8.dp, end = 16.dp),
        text = title,
        style = TextStyle(
            color = ColorSingleton.appPalette.value.primaryInverce,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight(500)
        ),
    )
}

@Composable
fun DepositItem(item: Deposit, onClick:(depositId: Long)-> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable{
                onClick.invoke(item.id)
            }
            .background(ColorSingleton.appPalette.value.surface)
    ) {
        Card(
            modifier = Modifier
                .padding(start = 22.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                modifier = Modifier.background(ColorSingleton.appPalette.value.background),
                painter = painterResource(id = R.drawable.leading_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(ColorSingleton.appPalette.value.primary)
            )
        }
        Column (
            modifier = Modifier
                .padding(start = 31.dp, top = 16.dp, end = 31.dp)
                .weight(1f)
        ){
            Text(
                text = LanguageSingleton.localization.value.getForDepo(item.name),
                maxLines = 1,
                style = TextStyle(
                    color = ColorSingleton.appPalette.value.onSecondary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600)
                ),
            )
            Text(
                text = item.balance.toString(),
                maxLines = 1,
                style = TextStyle(
                    color = ColorSingleton.appPalette.value.primaryInverce,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600)
                ),
            )
        }
        Text(
            modifier = Modifier.padding(end = 80.dp),
            text = "${item.percentType} %",
            maxLines = 1,
            style = TextStyle(
                color = ColorSingleton.appPalette.value.onSecondary,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight(600)
            ),
        )
    }
}

@Composable
fun CreditItem(item: Credit, onClick:(creditId: Long)-> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable{
                onClick.invoke(item.id)
            }
    ) {
        Card(
            modifier = Modifier
                .padding(start = 22.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                modifier = Modifier.background(ColorSingleton.appPalette.value.background),
                painter = painterResource(id =
                    if (item.name == "Автокредит")
                        R.drawable.auto_credit_icon
                    else
                        R.drawable.mortgage_icon
                ),
                contentDescription = null,
                colorFilter = ColorFilter.tint(ColorSingleton.appPalette.value.primary)
            )
        }
        Column (
            modifier = Modifier
                .padding(start = 31.dp, top = 16.dp, end = 31.dp)
                .weight(1f)
        ){
            Text(
                text = LanguageSingleton.localization.value.getForFinance(item.name),
                maxLines = 1,
                style = TextStyle(
                    color = ColorSingleton.appPalette.value.onSecondary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600)
                ),
            )
            Text(
                text = item.balance.toString(),
                maxLines = 1,
                style = TextStyle(
                    color = ColorSingleton.appPalette.value.primaryInverce,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600)
                ),
            )
        }
    }
}

