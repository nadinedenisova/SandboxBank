package com.example.sandboxbank.App.ui.financialitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.App.core.deposit.domain.db.FinancialItemRepository
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FinancialItemDetailsViewModel @Inject constructor(
    private val financialItemRepository: FinancialItemRepository,
    //userIdProvider: () -> Long,
    private val internetUtil: InternetUtil,
) : ViewModel() {

    private val _financialItem = MutableStateFlow<FinancialItem?>(null)
    val financialItem: StateFlow<FinancialItem?> = _financialItem

    private val _uiState = MutableStateFlow<FinancialItemDetailsUIState>(
        FinancialItemDetailsUIState.Loading
    )
    val uiState: StateFlow<FinancialItemDetailsUIState> = _uiState


    private var currentId: Long? = null

    //val userId = userIdProvider()
    val userId = 1L


    /**
     * грузим данные по id
     */
    fun loadFinancialItem(id: Long) {
        _uiState.value = FinancialItemDetailsUIState.Loading
        currentId = id
        viewModelScope.launch {
            delay(1500)
            val result = financialItemRepository.getById(id, userId)
            if (result.isSuccess) {
                val value = result.getOrNull()
                if (value != null && value is FinancialItem) {
                    _uiState.value = FinancialItemDetailsUIState.Success(value)
                } else {
                    _uiState.value = FinancialItemDetailsUIState.Error
                }
            } else {
                _uiState.value = FinancialItemDetailsUIState.Error
            }
        }
    }

    // Если нужно повторить попытку загрузки
    fun retryLoading() {
        val id = currentId ?: return
        loadFinancialItem(id)
    }
}