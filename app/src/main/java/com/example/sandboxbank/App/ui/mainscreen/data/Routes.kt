package com.example.sandboxbank.App.ui.mainscreen.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sandboxbank.R

enum class Routes(
    val route: String,
    val title: @Composable () -> String,
    val icon: Int?
) {
    Cards(
        route = "cards",
        title = { stringResource(R.string.bottomnav_cards) },
        icon = R.drawable.icon_card
    ),
    Finance(
        route = "finance",
        title = { stringResource(R.string.bottomnav_finances) },
        icon = R.drawable.icon_finance
    ),
    Transaction(
        route = "transaction",
        title = { stringResource(R.string.bottomnav_transactions) },
        icon = R.drawable.icon_transaction
    ),
    History(
        route = "history",
        title = { stringResource(R.string.bottomnav_history) },
        icon = R.drawable.icon_history
    ),
    Profile(
        route = "profile",
        title = { stringResource(R.string.bottomnav_profile) },
        icon = R.drawable.icon_profile
    ),
    Deposit(
        route = "deposit/{depositId}",
        title = { stringResource(R.string.deposit) },
        icon = null
    ),
    Credit(
        route = "credit/{creditId}",
        title = { stringResource(R.string.credit) },
        icon = null
    ),
    ApplyCredit(
        route = "applyCredit",
        title = { stringResource(R.string.apply_credit_button_apply) },
        icon = null
    );

    companion object {
        fun depositDetails(depositId: Long) = "deposit/$depositId"
        fun creditDetails(creditId: Long) = "credit/$creditId"
    }
}