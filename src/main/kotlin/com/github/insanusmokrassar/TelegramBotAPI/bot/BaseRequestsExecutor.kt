package com.github.insanusmokrassar.TelegramBotAPI.bot

abstract class BaseRequestsExecutor(
    token: String,
    hostUrl: String = "https://api.telegram.org"
) : RequestsExecutor {
    protected val baseUrl: String = "$hostUrl/bot$token"
}