package com.example.sandboxbank.domain.model

data class User(
    val id: Int,
    val email: String,
    val accessToken: String,
    val refreshToken: String
)