package com.example.sandboxbank.domain.usecase

import com.example.sandboxbank.data.auth.model.AuthResponse
import com.example.sandboxbank.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResponse {
        return authRepository.login(email, password)
    }
}