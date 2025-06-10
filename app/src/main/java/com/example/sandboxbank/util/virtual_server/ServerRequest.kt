package com.example.sandboxbank.util.virtual_server

sealed interface ServerRequest {

    val httpMethod: HttpMethods

    data class Registration(
        override val httpMethod: HttpMethods,
        var email: String,
        var password: String,
    ):ServerRequest

    data class Auth(
        override val httpMethod: HttpMethods,
        var email: String,
        var password: String,
    ):ServerRequest

    data class Refresh(
        override val httpMethod: HttpMethods,
        var userID: Long,
        var refreshToken: String,
    ):ServerRequest


}