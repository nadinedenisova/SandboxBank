package com.example.sandboxbank.App.ui.financialScreen.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.App.core.deposit.domain.db.FinancialItemRepository
import com.example.sandboxbank.App.core.deposit.domain.model.Credit
import com.example.sandboxbank.App.core.deposit.domain.model.Deposit
import com.example.sandboxbank.App.ui.financialScreen.data.FinanceState
import com.example.sandboxbank.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinancialScreenViewModel @Inject constructor(
    private val financialItemRepository: FinancialItemRepository,
) : ViewModel()  {
    private val _stateDeposFlow = MutableStateFlow<FinanceState>(FinanceState.ContentDeposits(deposits = emptyList()))
    val stateDeposFlow = _stateDeposFlow.asStateFlow()
    private val _stateCreditsFlow = MutableStateFlow<FinanceState>(FinanceState.ContentCredits(credits = emptyList()))
    val stateCreditsFlow = _stateCreditsFlow.asStateFlow()
    val userId = 1L

    fun load() {
        viewModelScope.launch {
            _stateDeposFlow.value = FinanceState.Loading
            financialItemRepository.getAllDeposits(userId).let {
                if (it.isSuccess){
                    val value = it.getOrNull()
                    if (value != null) {
                        _stateDeposFlow.value = FinanceState.ContentDeposits(
                            deposits = value.map { item ->
                                Deposit(
                                    id = item.id,
                                    type = item.type,
                                    openDate = item.openDate,
                                    percentRate = item.percentRate,
                                    percentType = item.percentType,
                                    period = item.period,
                                    balance = item.balance,
                                    name = item.name
                                )
                            })
                    } else {
                        _stateDeposFlow.value = FinanceState.Error(
                            errorMessageId = R.string.transaction_error
                        )
                    }
                } else {
                    _stateDeposFlow.value = FinanceState.Error(
                        errorMessageId = R.string.transaction_error
                    )
                }
            }
        }
        viewModelScope.launch {
            financialItemRepository.getAllCredits(userId).let {
                if (it.isSuccess){
                    val value = it.getOrNull()
                    if (value != null) {
                        _stateCreditsFlow.value = FinanceState.ContentCredits(
                            credits = value.map { item ->
                                Credit(
                                    id = item.id,
                                    type = item.type,
                                    openDate = item.openDate,
                                    percentRate = item.percentRate,
                                    percentType = item.percentType,
                                    period = item.period,
                                    balance = item.balance,
                                    name = item.name
                                )
                            })
                    } else {
                        _stateCreditsFlow.value = FinanceState.Error(
                            errorMessageId = R.string.transaction_error
                        )
                    }
                } else {
                    _stateCreditsFlow.value = FinanceState.Error(
                        errorMessageId = R.string.transaction_error
                    )
                }
            }
        }
    }
}
