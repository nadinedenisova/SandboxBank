package com.example.sandboxbank.util.virtual_server

class ResponseRegAuth(
    var accessToken: String,
    var refreshToken: String,
    var userId: Long,
):Response()