package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

interface KtorCallFactory {
    suspend fun <T: Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T?
}
