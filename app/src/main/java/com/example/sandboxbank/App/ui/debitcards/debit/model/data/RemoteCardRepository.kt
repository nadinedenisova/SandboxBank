package com.example.sandboxbank.App.ui.debitcards.debit.model.data

import com.example.sandboxbank.Api
import com.example.sandboxbank.App.ui.debitcards.debit.mapper.toLocalModel
import com.example.sandboxbank.CreateCardRequest
import com.example.sandboxbank.cardmanager.cards.entity.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RemoteCardRepository(
    private val api: Api,
    private val accessTokenProvider: suspend () -> String
) {
    suspend fun createDebitCard(
        userId: Long,
        currentCardNumber: Long,
        requestNumber: Long
    ): Result<Card> = withContext(Dispatchers.IO) {
        try {
            val token = accessTokenProvider()
            val request = CreateCardRequest(userId, requestNumber, currentCardNumber)
            val response = api.createDebitCard(request, "Bearer $token")

            if (response.isSuccessful) {
                val card = response.body()?.card
                if (card != null) {
                    Result.success(card.toLocalModel())
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllCards(userId: Long): Result<List<Card>> = withContext(Dispatchers.IO) {
        try {
            val token = accessTokenProvider()
            val response = api.getAllCards(userId, "Bearer $token")

            if (response.isSuccessful) {
                val cards = response.body()?.cards?.map { it.toLocalModel() } ?: emptyList()
                Result.success(cards)
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
