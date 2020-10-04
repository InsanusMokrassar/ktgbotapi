package dev.inmo.tgbotapi.bot.Ktor

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
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
