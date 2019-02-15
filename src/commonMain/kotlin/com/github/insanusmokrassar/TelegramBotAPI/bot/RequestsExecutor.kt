package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request

interface RequestsExecutor {
    /**
     * @throws RequestException
     */
    suspend fun <T : Any> execute(request: Request<T>): T
}