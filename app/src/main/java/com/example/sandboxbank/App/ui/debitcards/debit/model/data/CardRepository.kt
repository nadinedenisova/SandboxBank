package com.example.sandboxbank.cardmanager.cards.debit.model.data

import android.content.SharedPreferences
import com.example.sandboxbank.R
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import com.example.sandboxbank.cardmanager.cards.dto.CardResponse
import com.example.sandboxbank.cardmanager.cards.entity.Card
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepository(
    private val sharedPrefs: SharedPreferences
) {
    companion object {
        private const val MAX_CARDS = 5
        private const val CARD_COUNT_KEY = "card_count"
    }

    fun getCardCount(): Int {
        return sharedPrefs.getInt(CARD_COUNT_KEY, 0)
    }

    private fun incrementCardCount() {
        val count = getCardCount() + 1
        sharedPrefs.edit().putInt(CARD_COUNT_KEY, count).apply()
    }

    fun createCard(request: CardRequest): Flow<Result<CardResponse>> = flow {
        val count = getCardCount()
        if (count >= MAX_CARDS) {
            emit(Result.failure(Exception(R.string.maximum_5_cards.toString())))
            return@flow
        }

        delay(1000)

        val card = Card(
            id = 2200000000000000 + count,
            cvv = (100..999).random().toLong(),
            endDate = "2028-02-02",
            owner = "Ivan Ivanov",
            type = "debit",
            percent = 0.0,
            balance = 0
        )

        val response = CardResponse(
            currentCardNumber = request.currentCardNumber,
            requestNumber = request.requestNumber,
            card = card
        )

        incrementCardCount()
        emit(Result.success(response))
    }
}
