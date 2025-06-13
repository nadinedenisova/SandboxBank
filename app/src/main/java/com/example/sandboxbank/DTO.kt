package com.example.sandboxbank

data class RegistrationRequest(
    val email: String,
    val password: String
)

data class RegistrationResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long
)

data class RefreshRequest(
    val email: String,
    val refreshToken: String
)

data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String
)

data class CreateCardRequest(
    val userId: Long,
    val requestNumber: Long,
    val currentCardNumber: Long
)

data class Card(
    val id: Long,
    val cvv: Int,
    val endDate: String,
    val owner: String,
    val type: String,
    val percent: Double,
    val balance: Int
)

data class CreateCardResponse(
    val card: Card,
    val requestNumber: Long,
    val currentCardNumber: Long
)

data class GetAllCardsResponse(
    val cards: List<Card>
)

data class CreateDepositRequest(
    val userId: Long,
    val currentDepositNumber: Long,
    val requestNumber: Long,
    val percentType: Long,
    val period: Long
)

data class Product(
    val id: Long,
    val type: String,
    val percentType: Long,
    val period: Long,
    val percent: Int,
    val balance: Long
)

data class CreateDepositResponse(
    val product: Product,
    val requestNumber: Long,
    val currentDepositNumber: Long
)

data class CreateCreditRequest(
    val userId: Long,
    val currentCreditNumber: Long,
    val requestNumber: Long,
    val balance: Long,
    val period: Long
)

data class CreateCreditResponse(
    val product: Product,
    val requestNumber: Long,
    val currentCreditNumber: Long
)

data class ProductsResponse(
    val products: List<Product>
)