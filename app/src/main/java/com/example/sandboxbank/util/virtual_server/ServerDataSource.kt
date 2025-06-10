package com.example.sandboxbank.util.virtual_server

import com.example.sandboxbank.util.Randomizer
import java.net.HttpURLConnection
import kotlin.random.Random

class ServerDataSource {

    companion object {
        const val PERCENT_SERVER_UNREACHABLE = 15 //% что сервер будет недоступен
        const val PERCENT_USER_NOT_EXIST = 75 //% что пользователя не существует
        const val TOKEN_LENGTH = 49
        const val MIN_USER_ID: Long = 0
        const val MAX_USER_ID: Long = 100_000_000_000
        const val MIN_PASSWORD_LENGTH = 7
    }


    fun registration(
        email: String,
        password: String,
    ): Response {

        //Доступность сервера
        val serverIsActive = Randomizer.trueOrFalse(PERCENT_SERVER_UNREACHABLE)
        val userExists = Randomizer.trueOrFalse(PERCENT_USER_NOT_EXIST)
        val accessToken = Randomizer.randomString(TOKEN_LENGTH)
        val refreshToken = Randomizer.randomString(TOKEN_LENGTH)
        val userId = Random.nextLong(MIN_USER_ID, MAX_USER_ID)

        if (!serverIsActive) {
            return Response().apply {
                httpCode = HttpURLConnection.HTTP_UNAVAILABLE
            }
        }

        if (userExists) {
            return Response().apply {
                httpCode = HttpURLConnection.HTTP_CONFLICT
            }
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return Response().apply {
                httpCode = HttpURLConnection.HTTP_BAD_REQUEST
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Response().apply {
                httpCode = HttpURLConnection.HTTP_BAD_REQUEST
            }
        }

        return ResponseRegAuth(
            userId = userId,
            accessToken = accessToken,
            refreshToken = refreshToken,
        ).apply {
            httpCode = HttpURLConnection.HTTP_CREATED
        }
    }

    fun auth(
        email: String,
        password: String,
    ):Response {

        val accessToken = Randomizer.randomString(TOKEN_LENGTH)
        val refreshToken = Randomizer.randomString(TOKEN_LENGTH)
        val userId = Random.nextLong(MIN_USER_ID, MAX_USER_ID)


        if (password.length < MIN_PASSWORD_LENGTH) {
            return Response().apply {
                httpCode = HttpURLConnection.HTTP_BAD_REQUEST
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Response().apply {
                httpCode = HttpURLConnection.HTTP_BAD_REQUEST
            }
        }

        return ResponseRegAuth(
            accessToken = accessToken,
            refreshToken = refreshToken,
            userId = userId,
        ).apply {
            httpCode = HttpURLConnection.HTTP_OK
        }


    }

}