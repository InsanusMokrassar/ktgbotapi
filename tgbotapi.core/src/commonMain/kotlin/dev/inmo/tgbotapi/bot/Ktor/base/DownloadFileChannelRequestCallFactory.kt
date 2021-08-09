package dev.inmo.tgbotapi.bot.Ktor.base

import dev.inmo.tgbotapi.bot.Ktor.KtorCallFactory
import dev.inmo.tgbotapi.requests.DownloadFileStream
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.ByteReadChannelAllocator
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.coroutineContext

object DownloadFileChannelRequestCallFactory : KtorCallFactory {
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? = (request as? DownloadFileStream) ?.let {
        val fullUrl = urlsKeeper.createFileLinkUrl(it.filePath)

        return ByteReadChannelAllocator {
            val scope = CoroutineScope(coroutineContext)
            val outChannel = ByteChannel()
            scope.launch {
                client.get<HttpStatement>(fullUrl).execute { httpResponse ->
                    val channel: ByteReadChannel = httpResponse.receive()
                    channel.copyTo(outChannel)
                }
            }
            outChannel
        } as T
    }
}
