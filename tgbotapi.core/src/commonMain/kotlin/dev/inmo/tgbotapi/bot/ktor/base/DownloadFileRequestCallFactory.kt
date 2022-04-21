package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.requests.DownloadFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

object DownloadFileRequestCallFactory : KtorCallFactory {
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? = (request as? DownloadFile) ?.let {
        val fullUrl = urlsKeeper.createFileLinkUrl(it.filePath)

        safely {
            @Suppress("UNCHECKED_CAST")
            client.get<ByteArray>(fullUrl) as T // always ByteArray
        }
    }
}
