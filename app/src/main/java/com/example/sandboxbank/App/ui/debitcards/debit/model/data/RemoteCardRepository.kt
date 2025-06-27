package com.example.sandboxbank.cardmanager.cards.debit.model.data

import android.content.SharedPreferences
import com.example.sandboxbank.App.ui.debitcards.debit.mapper.toLocalModel
import com.example.sandboxbank.CreateCardRequest
import com.example.sandboxbank.CreateCardResponse
import com.example.sandboxbank.cardmanager.cards.entity.Card
import com.example.sandboxbank.Api
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

sealed class CardCreationResult {
    data class Success(val card: Card) : CardCreationResult()
    data class LimitReached(val message: String) : CardCreationResult()
    data class Error(val message: String) : CardCreationResult()
}

class RemoteCardRepository @Inject constructor(
    private val api: Api,
    private val sharedPrefs: SharedPreferences,
    private val gson: Gson
) {
    private var mockRequestNumber = 1L

    suspend fun createDebitCard(
        userId: Long,
        currentCardNumber: Long,
        requestNumber: Long,
        accessToken: String = "Fake mocked_token"
    ): CardCreationResult {
        if (getCardCount() >= MAX_CARDS) {
            return CardCreationResult.LimitReached("Maximum card limit reached 5 UNIT.")
        }

        val response: Response<CreateCardResponse> = try {
            api.createDebitCard(
                request = CreateCardRequest(
                    userId = userId,
                    requestNumber = requestNumber,
                    currentCardNumber = currentCardNumber
                ),
                accessToken = accessToken
            )
        } catch (e: Exception) {
            return CardCreationResult.Error("Network error: ${e.localizedMessage}")
        }

        return if (response.isSuccessful && response.body() != null) {
            val body = response.body()!!
            val localCard = body.card.toLocalModel()
            saveCard(localCard)
            CardCreationResult.Success(localCard)
        } else {
            val mockedCard = Card(
                id = System.currentTimeMillis(),
                cvv = 999,
                endDate = "2028-12-31",
                owner = "Ivan Ivanov",
                type = "debit",
                percent = 0.0,
                balance = 0L
            )
            saveCard(mockedCard)
            CardCreationResult.Success(mockedCard)
        }
    }

    fun getCardCount(): Int = sharedPrefs.getInt(CARD_COUNT_KEY, 0)

    private fun incrementCardCount() {
        val count = getCardCount() + 1
        sharedPrefs.edit().putInt(CARD_COUNT_KEY, count).apply()
    }

    fun saveCard(card: Card) {
        val index = getCardCount()
        if (index >= MAX_CARDS) return
        val cardJson = gson.toJson(card)
        sharedPrefs.edit().putString("$CARD_KEY_PREFIX$index", cardJson).apply()
        incrementCardCount()
    }

    fun getAllCards(): List<Card> {
        val count = getCardCount()
        return (0 until count).mapNotNull { index ->
            sharedPrefs.getString("$CARD_KEY_PREFIX$index", null)?.let {
                gson.fromJson(it, Card::class.java)
            }
        }
    }

    companion object {
        private const val MAX_CARDS = 5
        private const val CARD_COUNT_KEY = "card_count"
        private const val CARD_KEY_PREFIX = "card_"
    }
}
