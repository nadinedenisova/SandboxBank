package com.example.sandboxbank.App.ui.creditcard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.creditcard.item.CustomCreditSlider
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.CardDescriptionItem
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.CardView
import com.example.sandboxbank.R

@Composable
fun CreditCardScreen(
    onSubmit: (Int) -> Unit
) {
    val minCreditLimit = 0f
    val maxCreditLimit = 1_000_000f
    val steps = 100_000f

    var creditLimit by remember { mutableStateOf(500_000f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        CardView()

        Text(
            text = stringResource(R.string.virtual_card_bank_debit),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.5.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CardDescriptionItem(
            title = stringResource(R.string.free_usage),
            value = stringResource(R.string.free_usage_value)
        )
        CardDescriptionItem(
            title = stringResource(R.string.one_million),
            value = stringResource(R.string.maximal_limit)
        )
        CardDescriptionItem(
            title = stringResource(R.string.six_months),
            value = stringResource(R.string.for_all_shopping)
        )
        CardDescriptionItem(
            title = stringResource(R.string.cashback),
            value = stringResource(R.string.partnership_buying)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.chosen_credit_limit),
            style = MaterialTheme.typography.subtitle1.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )


        Spacer(modifier = Modifier.height(8.dp))

        CustomCreditSlider(
            min = minCreditLimit,
            max = maxCreditLimit,
            step = steps,
            value = creditLimit,
            onValueChange = { creditLimit = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSubmit(creditLimit.toInt()) },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF65558F),
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(stringResource(R.string.submit_card))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreditCardIssueScreenPreview() {
    MaterialTheme {
        CreditCardScreen(onSubmit = {})
    }
}
