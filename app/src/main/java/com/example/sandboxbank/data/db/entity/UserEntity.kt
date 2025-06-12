package com.example.sandboxbank.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val email: String,
    val accessToken: String,
    val refreshToken: String,
    val failedAttempts: Int = 0,
    val lastFailedAttemptTime: Long? = null
)
