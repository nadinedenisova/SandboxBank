package com.example.sandboxbank

import com.example.sandboxbank.transaction.data.dto.TransferRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("registration")
    suspend fun register(
        @Body request: RegistrationRequest
    ): Response<RegistrationResponse>

    @POST("auth")
    suspend fun auth(
        @Body request: RegistrationRequest
    ): Response<RegistrationResponse>

    @POST("refresh")
    suspend fun refreshToken(
        @Body request: RefreshRequest
    ): Response<RefreshResponse>

    @POST("card/debit/create")
    suspend fun createDebitCard(
        @Body request: CreateCardRequest,
        @Header("Authorization") accessToken: String //"Bearer $accessToken"
    ): Response<CreateCardResponse>

    @POST("card/credit/create")
    suspend fun createCreditCard(
        @Body request: CreateCardRequest,
        @Header("Authorization") accessToken: String //"Bearer $accessToken"
    ): Response<CreateCardResponse>

    @GET("/cards")
    suspend fun getAllCards(
        @Query("user_id") userId: Long,
        @Header("Authorization") accessToken: String //"Bearer $accessToken"
    ): Response<GetAllCardsResponse>

    @POST("/deposit/create")
    suspend fun createDeposit(
        @Body request: CreateDepositRequest,
        @Header("Authorization") accessToken: String //"Bearer $accessToken"
    ): Response<CreateDepositResponse>

    @POST("/credit/create")
    suspend fun createCredit(
        @Body request: CreateCreditRequest,
        @Header("Authorization") accessToken: String //"Bearer $accessToken"
    ): Response<CreateCreditResponse>

    @GET("/products")
    suspend fun getProducts(
        @Query("user_id") userId: Long,
        @Header("Authorization") accessToken: String //"Bearer $accessToken"
    ): Response<ProductsResponse>

    @POST("/transfer")
    suspend fun makeTransfer(
        @Body request: TransferRequest,
        @Header("Authorization") accessToken: String
    ): Response<Unit>
}