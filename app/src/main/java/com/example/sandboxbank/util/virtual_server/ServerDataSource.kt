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
        val EMAIL_PATTERN = android.util.Patterns.EMAIL_ADDRESS
    }


    fun registration(
        request: ServerRequest.Registration
    ): ServerResponse {

        //Доступность сервера
        val serverIsActive = Randomizer.trueOrFalse(PERCENT_SERVER_UNREACHABLE)
        val isUserExist = Randomizer.trueOrFalse(PERCENT_USER_NOT_EXIST)
        val accessToken = Randomizer.randomToken(TOKEN_LENGTH)
        val refreshToken = Randomizer.randomToken(TOKEN_LENGTH)
        val userId = Random.nextLong(MIN_USER_ID, MAX_USER_ID)

        if (!serverIsActive) {
            return ServerResponse.Empty(httpCode = HttpURLConnection.HTTP_UNAVAILABLE)
        }

        if (isUserExist) {
            return ServerResponse.Empty(httpCode = HttpURLConnection.HTTP_CONFLICT)
        }

        if (!isCredentialsCorrect(request.password, request.email)) {
            return ServerResponse.Empty(httpCode = HttpURLConnection.HTTP_BAD_REQUEST)
        }

        return ServerResponse.Registration(
            userId = userId,
            accessToken = accessToken,
            refreshToken = refreshToken,
            httpCode = HttpURLConnection.HTTP_CREATED
        )
    }

    fun auth(
        request: ServerRequest.Auth
    ): ServerResponse {

        val accessToken = Randomizer.randomToken(TOKEN_LENGTH)
        val refreshToken = Randomizer.randomToken(TOKEN_LENGTH)
        val userId = Random.nextLong(MIN_USER_ID, MAX_USER_ID)

        if (!isCredentialsCorrect(request.password, request.email)) {
            return ServerResponse.Empty(httpCode = HttpURLConnection.HTTP_BAD_REQUEST)
        }

        return ServerResponse.Auth(
            userId = userId,
            accessToken = accessToken,
            refreshToken = refreshToken,
            httpCode = HttpURLConnection.HTTP_CREATED
        )

    }

    fun refresh(
        request: ServerRequest.Refresh
    ): ServerResponse {

        with(request){
            return if (refreshToken.length == TOKEN_LENGTH && userID in 0..100_000_000_000)
                ServerResponse.Refresh(
                    refreshToken = Randomizer.randomToken(TOKEN_LENGTH),
                    accessToken = Randomizer.randomToken(TOKEN_LENGTH),
                    httpCode = HttpURLConnection.HTTP_CREATED
                ) else {
                ServerResponse.Empty(
                    httpCode = HttpURLConnection.HTTP_BAD_REQUEST
                )
            }
        }


    }

    private fun isCredentialsCorrect(pass: String, email: String): Boolean {
        return pass.length > MIN_PASSWORD_LENGTH &&
                EMAIL_PATTERN.matcher(email).matches()

    }


}