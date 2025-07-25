package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.requests.DownloadFileStream
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.ByteReadChannelAllocator
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

@RiskFeature
object DownloadFileChannelRequestCallFactory : KtorCallFactory {
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? = (request as? DownloadFileStream) ?.let {
        val bodyChannelAllocator: suspend () -> ByteReadChannel = resolveFile(it.filePath) ?.let {
            {
                byteReadChannel(it)
            }
        } ?: let { _ ->
            val fullUrl = urlsKeeper.createFileLinkUrl(it.filePath)
            return@let {
                val response = client.get(fullUrl)
                response.bodyAsChannel()
            }
        }

        @Suppress("UNCHECKED_CAST")
        ByteReadChannelAllocator {
            val scope = CoroutineScope(currentCoroutineContext() + SupervisorJob())
            val outChannel = ByteChannel()
            scope.launch {
                runCatching {
                    val channel: ByteReadChannel = bodyChannelAllocator()
                    channel.copyAndClose(outChannel)
                }
                scope.cancel()
            }
            outChannel
        } as T
    }
}
