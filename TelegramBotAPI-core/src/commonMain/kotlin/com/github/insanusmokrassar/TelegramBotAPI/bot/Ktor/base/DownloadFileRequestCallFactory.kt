package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.DownloadFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import com.github.insanusmokrassar.TelegramBotAPI.utils.handleSafely
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
