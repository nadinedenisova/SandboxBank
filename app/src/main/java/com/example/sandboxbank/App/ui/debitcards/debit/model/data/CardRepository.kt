package com.example.sandboxbank.cardmanager.cards.debit.model.data

import android.content.SharedPreferences
import com.example.sandboxbank.R
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import com.example.sandboxbank.cardmanager.cards.dto.CardResponse
import com.example.sandboxbank.cardmanager.cards.entity.Card
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepository(
    private val sharedPrefs: SharedPreferences,
    private val gson: Gson
) {

    fun getCardCount(): Int {
        return sharedPrefs.getInt(CARD_COUNT_KEY, 0)
    }

    private fun incrementCardCount() {
        val count = getCardCount() + 1
        sharedPrefs.edit().putInt(CARD_COUNT_KEY, count).apply()
    }

    fun saveCard(card: Card) {
        val index = getCardCount()
        if (index >= MAX_CARDS) return
        val cardJson = gson.toJson(card)
        sharedPrefs.edit()
            .putString("$CARD_KEY_PREFIX$index", cardJson)
            .apply()
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

