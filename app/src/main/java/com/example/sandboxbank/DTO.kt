package com.example.sandboxbank

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegistrationResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long
)

@Serializable
data class RefreshRequest(
    val email: String,
    val refreshToken: String
)

@Serializable
data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class CreateCardRequest(
    val userId: Long,
    val requestNumber: Long,
    val currentCardNumber: Long
)

@Serializable
data class Card(
    val id: Long,
    val cvv: Int,
    val endDate: String,
    val owner: String,
    val type: String,
    val percent: Double,
    val balance: Int
)

@Serializable
data class CreateCardResponse(
    val card: Card,
    val requestNumber: Long,
    val currentCardNumber: Long
)

@Serializable
data class GetAllCardsResponse(
    val cards: List<Card>
)

@Serializable
data class CreateDepositRequest(
    val userId: Long,
    val currentDepositNumber: Long,
    val requestNumber: Long,
    val percentType: Long,
    val period: Long
)

@Serializable
data class Product(
    val id: Long,
    val type: String,
    val percentType: Long,
    val period: Long,
    val percent: Int,
    val balance: Long
)

@Serializable
data class CreateDepositResponse(
    val product: Product,
    val requestNumber: Long,
    val currentDepositNumber: Long
)

@Serializable
data class CreateCreditRequest(
    val userId: Long,
    val currentCreditNumber: Long,
    val requestNumber: Long,
    val balance: Long,
    val period: Long
)

@Serializable
data class CreateCreditResponse(
    val product: Product,
    val requestNumber: Long,
    val currentCreditNumber: Long
)

@Serializable
data class ProductsResponse(
    val products: List<Product>
)

@Serializable
data class TransactionRequest(
    val fromId: Long,
    val fromType: String,
    val toId: Long,
    val toType: String,
    val value: Long,
    val transactionNumber: Long,
    val userId: Long
)

@Serializable
data class TransactionResponse(
    val transactionNumber: Long
)

@Serializable
data class SkyTopUpRequest(
    val toId: Long,
    val toType: String,
    val value: Long,
    val transactionNumber: Long
)

data class SkyTopUpResponse(
    val transactionNumber: Long
)