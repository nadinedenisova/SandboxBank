package com.example.sandboxbank.data.auth

import android.util.Patterns
import com.example.sandboxbank.data.auth.model.AuthResponse
import com.example.sandboxbank.data.db.entity.UserEntity
import javax.inject.Inject
import kotlin.random.Random

class AuthDataSource @Inject constructor(
    // Нужно реализовать apiService и authDao
    private val apiService: Unit,
    private val authDao: Unit
) {
    private val random = Random(System.currentTimeMillis())

    suspend fun register(email: String, password: String): AuthResponse {
        TODO()

    }

    suspend fun login(email: String, password: String): AuthResponse {
        TODO()
    }

    private fun generateToken(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..40)
            .map { random.nextInt(charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}