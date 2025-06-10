package com.example.sandboxbank.util.virtual_server

sealed interface ServerResponse {

    val header: Pair<String, String>?
    val httpCode: Int

    data class Empty(
        override val header: Pair<String, String>? = null,
        override val httpCode: Int,
    ):ServerResponse

    data class Registration(
        override val header: Pair<String, String>? = null,
        var accessToken: String,
        var refreshToken: String,
        var userId: Long,
        override val httpCode: Int,
    ):ServerResponse

    data class Auth(
        override val header: Pair<String, String>? = null,
        var accessToken: String,
        var refreshToken: String,
        var userId: Long,
        override val httpCode: Int,
    ):ServerResponse

    data class Refresh(
        override val header: Pair<String, String>? = null,
        var accessToken: String,
        var refreshToken: String,
        override val httpCode: Int,
    ):ServerResponse

}