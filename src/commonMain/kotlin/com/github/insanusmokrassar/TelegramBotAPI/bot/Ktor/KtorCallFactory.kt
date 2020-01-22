package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpStatement

interface KtorCallFactory {
    suspend fun <T: Any> prepareCall(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ) : HttpStatement?
}
