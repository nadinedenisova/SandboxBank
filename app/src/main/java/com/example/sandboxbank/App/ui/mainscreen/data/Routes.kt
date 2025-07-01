package com.example.sandboxbank.App.ui.mainscreen.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.sandboxbank.R

enum class Routes(
    val route: String,
    val title: @Composable () -> String,
    val icon: ImageVector?
) {
    Cards(
        route = "cards",
        title = { stringResource(R.string.bottomnav_cards) },
        icon = Icons.Default.Info
    ),
    Finance(
        route = "finance",
        title = { stringResource(R.string.bottomnav_finances) },
        icon = Icons.Default.Info
    ),
    Transaction(
        route = "transaction",
        title = { stringResource(R.string.bottomnav_transactions) },
        icon = Icons.Default.Info
    ),
    History(
        route = "history",
        title = { stringResource(R.string.bottomnav_history) },
        icon = Icons.Default.Info
    ),
    Profile(
        route = "profile",
        title = { stringResource(R.string.bottomnav_profile) },
        icon = Icons.Default.Info
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