package com.example.sandboxbank.App.ui.financialScreen.domain

import androidx.lifecycle.ViewModel
import com.example.sandboxbank.App.core.deposit.data.FinancialType
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.ui.financialScreen.data.FinanceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import javax.inject.Inject

class FinancialScreenViewModel @Inject constructor(
) : ViewModel()  {
    private val _stateDeposFlow = MutableStateFlow<FinanceState>(FinanceState.ContentDeposits(deposits = emptyList()))
    val stateDeposFlow = _stateDeposFlow.asStateFlow()
    private val _stateCreditsFlow = MutableStateFlow<FinanceState>(FinanceState.ContentCredits(credits = emptyList()))
    val stateCreditsFlow = _stateCreditsFlow.asStateFlow()

    fun load() {
        _stateDeposFlow.value = FinanceState.ContentDeposits(
            deposits = listOf(
                Deposit(
                    id = 1,
                    type = FinancialType.DEPOSIT,
                    openDate = 22222,
                    percentType = 12,
                    period = 3333,
                    balance = BigDecimal(10000.0),
                    percentRate = 8.9,
                    name = "Накопительный вклад"
                ),
                Deposit(
                    id = 1,
                    type = FinancialType.DEPOSIT,
                    openDate = 22222,
                    percentType = 12,
                    period = 3333,
                    balance = BigDecimal(33000.0),
                    percentRate = 8.0,
                    name = "До востребования"
                ))
        )

        _stateCreditsFlow.value = FinanceState.ContentCredits(
            credits = listOf(
                Credit(
                    id = 1,
                    type = FinancialType.CREDIT,
                    openDate = 22222,
                    percentType = 12,
                    period = 3333,
                    balance = BigDecimal(10000.0),
                    percentRate = 8.9,
                    name = "Автокредит"
                ),
                Credit(
                    id = 1,
                    type = FinancialType.CREDIT,
                    openDate = 22222,
                    percentType = 12,
                    period = 3333,
                    balance = BigDecimal(10000.0),
                    percentRate = 8.0,
                    name = "Ипотека"
                ))
        )
    }
}
