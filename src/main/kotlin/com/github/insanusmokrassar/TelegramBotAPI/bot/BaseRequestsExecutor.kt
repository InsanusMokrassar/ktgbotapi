package com.github.insanusmokrassar.TelegramBotAPI.bot

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

abstract class BaseRequestsExecutor(
    token: String,
    hostUrl: String = "https://api.telegram.org"
) : RequestsExecutor {
    protected val baseUrl: String = "$hostUrl/bot$token"

    protected val scope: CoroutineScope = CoroutineScope(
        Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    )
}