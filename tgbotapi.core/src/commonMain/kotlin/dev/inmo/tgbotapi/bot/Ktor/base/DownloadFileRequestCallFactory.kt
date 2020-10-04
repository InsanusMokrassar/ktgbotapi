package dev.inmo.tgbotapi.bot.Ktor.base

import dev.inmo.tgbotapi.bot.Ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.handleSafely
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

object DownloadFileRequestCallFactory : KtorCallFactory {
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? = (request as? DownloadFile) ?.let {
        val fullUrl = "${urlsKeeper.fileBaseUrl}/${it.filePath}"

        return handleSafely {
            @Suppress("UNCHECKED_CAST")
            client.get<ByteArray>(fullUrl) as T // always ByteArray
        }
    }
}
