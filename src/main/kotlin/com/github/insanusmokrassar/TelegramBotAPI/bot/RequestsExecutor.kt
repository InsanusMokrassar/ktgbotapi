package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import kotlinx.io.core.Closeable

interface RequestsExecutor : Closeable {
    @Throws(RequestException::class)
    suspend fun <T : Any> execute(request: Request<T>): T
}