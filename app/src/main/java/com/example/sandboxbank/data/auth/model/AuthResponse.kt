package com.example.sandboxbank.data.auth.model

data class AuthResponse(
    val statusCode: Int,
    val statusMessage: String,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val userId: Int? = null
)