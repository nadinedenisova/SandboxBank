package com.example.sandboxbank.App.ui.financialitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.App.core.deposit.domain.db.FinancialItemRepository
import com.example.sandboxbank.App.core.deposit.domain.model.FinancialItem
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FinancialItemDetailsViewModel @Inject constructor(
    private val financialItemRepository: FinancialItemRepository
) : ViewModel() {

    private val _financialItem = MutableStateFlow<FinancialItem?>(null)
    val financialItem: StateFlow<FinancialItem?> = _financialItem

    private val _uiState = MutableStateFlow<FinancialItemDetailsUIState<FinancialItem>>(
        FinancialItemDetailsUIState.Loading
    )
    val uiState: StateFlow<FinancialItemDetailsUIState<FinancialItem>> = _uiState

    private var currentId: Long? = null

    // временный костыль
    val userId = 1L

    /**
     * грузим данные по id
     */
    fun loadFinancialItem(id: Long) {
        currentId = id

        viewModelScope.launch {
            financialItemRepository.getById(id, userId)
//                .collect { item ->
//                    _financialItem.value = item
//                    _uiState.value = FinancialItemDetailsUIState.Success(item)
//                }
        }
    }

    // Если нужно повторить попытку загрузки
    fun retryLoading() {
        val id = currentId ?: return
        loadFinancialItem(id)
    }
}