package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request

interface RequestsExecutor {
    @Throws(RequestException::class)
    suspend fun <T : Any> execute(request: Request<T>): T
}