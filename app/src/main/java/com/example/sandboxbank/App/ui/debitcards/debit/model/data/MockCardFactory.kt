package com.example.sandboxbank.App.ui.debitcards.debit.model.data

import com.example.sandboxbank.CreateCardRequest
import com.example.sandboxbank.CreateCardResponse
import com.example.sandboxbank.Card

class MockCardFactory {
    fun generateMockCard(): Card {
        return Card(
            id = 2200000000000000,
            cvv = 999,
            endDate = "2028-02-02",
            owner = "Ivan Ivanov",
            type = "debit",
            percent = 0.0,
            balance = 0
        )
    }

    fun wrapInResponse(request: CreateCardRequest): CreateCardResponse {
        return CreateCardResponse(
            card = generateMockCard(),
            requestNumber = request.requestNumber,
            currentCardNumber = request.currentCardNumber
        )
    }
}
