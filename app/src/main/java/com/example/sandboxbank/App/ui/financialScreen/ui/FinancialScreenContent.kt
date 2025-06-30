package com.example.sandboxbank.App.ui.financialScreen.ui

import androidx.compose.foundation.Image
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
import com.example.sandboxbank.App.ui.financialScreen.data.FinanceState
import com.example.sandboxbank.App.ui.financialScreen.domain.FinancialScreenViewModel
import com.example.sandboxbank.R

@Composable
fun FinancialScreenContent(
    viewModel: FinancialScreenViewModel,
    onDepositClick:(deposit: Deposit)-> Unit,
    onCreditClick:(credit: Credit)-> Unit,
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
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TitleContent(title = stringResource(R.string.deposits_title))

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
                containerColor = LightColorPalette.primary,
            ),
            shape = RoundedCornerShape(100.dp),
            onClick = {
            }
        ) {
            Text(
                text = stringResource(R.string.deposits_open_button),
                style = TextStyle(
                    color = LightColorPalette.background,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(500)
                ),
            )
        }

        TitleContent(title = stringResource(R.string.credits_title))

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
                containerColor = LightColorPalette.primary,
            ),
            shape = RoundedCornerShape(100.dp),
            onClick = {
            }
        ) {
            Text(
                text = stringResource(R.string.credit_open_button),
                style = TextStyle(
                    color = LightColorPalette.background,
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
            color = LightColorPalette.primaryInverce,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight(500)
        ),
    )
}

@Composable
fun DepositItem(item: Deposit, onClick:(deposit: Deposit)-> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable{
                onClick.invoke(item)
            }
    ) {
        Card(
            modifier = Modifier
                .padding(start = 22.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.leading_icon),
                contentDescription = null,
            )
        }
        Column (
            modifier = Modifier
                .padding(start = 31.dp, top = 16.dp, end = 31.dp)
                .weight(1f)
        ){
            Text(
                text = item.name,
                maxLines = 1,
                style = TextStyle(
                    color = LightColorPalette.onSecondary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600)
                ),
            )
            Text(
                text = item.balance.toString(),
                maxLines = 1,
                style = TextStyle(
                    color = LightColorPalette.primaryInverce,
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
                color = LightColorPalette.onSecondary,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight(600)
            ),
        )
    }
}

@Composable
fun CreditItem(item: Credit, onClick:(credit: Credit)-> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable{
                onClick.invoke(item)
            }
    ) {
        Card(
            modifier = Modifier
                .padding(start = 22.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id =
                    if (item.type == "Автокредит")
                        R.drawable.auto_credit_icon
                    else
                        R.drawable.mortgage_icon
                ),
                contentDescription = null,
            )
        }
        Column (
            modifier = Modifier
                .padding(start = 31.dp, top = 16.dp, end = 31.dp)
                .weight(1f)
        ){
            Text(
                text = item.name,
                maxLines = 1,
                style = TextStyle(
                    color = LightColorPalette.onSecondary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600)
                ),
            )
            Text(
                text = item.balance.toString(),
                maxLines = 1,
                style = TextStyle(
                    color = LightColorPalette.primaryInverce,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600)
                ),
            )
        }
    }
}

