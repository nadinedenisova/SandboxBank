package com.example.sandboxbank.history.ui.state

sealed class HistoryViewState {
    data object Preview : HistoryViewState()
    data object DebitCards : HistoryViewState()
    data object CreditCards : HistoryViewState()
    data object CurrentLoan: HistoryViewState()
    data object CurrentDeposits : HistoryViewState()
    data object Transfers : HistoryViewState()
}