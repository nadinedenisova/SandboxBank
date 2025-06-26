package com.example.sandboxbank.transfer.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TransferViewModel : ViewModel() {

    var selectedTab by mutableStateOf(0)
    var debitFrom by mutableStateOf("Дебетовая карта")
    var debitTo by mutableStateOf("")
    var amount by mutableStateOf("")
    var status by mutableStateOf<TransferStatus?>(null)

    val accounts = listOf("Дебетовая карта", "Кредитная карта", "Вклад", "Кредит")

    fun onTransferClick() {
        viewModelScope.launch {
            status = TransferStatus.Loading
            delay(2000)
            val connected = checkInternetConnection()
            status = if (connected) {
                TransferStatus.Success
            } else {
                TransferStatus.NoInternet
            }
        }
    }

    private fun checkInternetConnection(): Boolean {
        TODO()
    }

    fun resetStatus() {
        status = null
    }

    sealed class TransferStatus {
        object Success : TransferStatus()
        object NoInternet : TransferStatus()
        object Loading : TransferStatus()
    }
}
