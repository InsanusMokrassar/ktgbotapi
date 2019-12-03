package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import kotlinx.io.core.Closeable

interface RequestsExecutor : Closeable {
    /**
     * @throws com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
     */
    suspend fun <T : Any> execute(request: Request<T>): T
}