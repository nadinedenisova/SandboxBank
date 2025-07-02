package com.example.sandboxbank.App.ui.mainscreen.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sandboxbank.App.ui.designkit.mode.language.LanguageSingleton
import com.example.sandboxbank.R
import com.example.sandboxbank.App.ui.designkit.mode.language.*

enum class Routes(
    val route: String,
    val title: @Composable () -> String,
    val icon: Int?
) {
    Cards(
        route = "cards",
        title = { LanguageSingleton.localization.value.cards()},
        icon = R.drawable.icon_card
    ),
    Finance(
        route = "finance",
        title = { LanguageSingleton.localization.value.finance() },
        icon = R.drawable.icon_finance
    ),
    Transaction(
        route = "transaction",
        title = { LanguageSingleton.localization.value.transfers() },
        icon = R.drawable.icon_transaction
    ),
    History(
        route = "history",
        title = { LanguageSingleton.localization.value.history() },
        icon = R.drawable.icon_history
    ),
    Profile(
        route = "profile",
        //title = { stringResource(R.string.bottomnav_profile) },
        title = {LanguageSingleton.localization.value.profile()},
        icon = R.drawable.icon_profile
    ),
    Deposit(
        route = "deposit/{depositId}",
        title = { LanguageSingleton.localization.value.depositCard()},
        icon = null
    ),
    Credit(
        route = "credit/{creditId}",
        title = { LanguageSingleton.localization.value.credit() },
        icon = null
    ),
    ApplyCredit(
        route = "applyCredit",
        title = { LanguageSingleton.localization.value.creditOpen()},
        icon = null
    );

    companion object {
        fun depositDetails(depositId: Long) = "deposit/$depositId"
        fun creditDetails(creditId: Long) = "credit/$creditId"
    }
}