package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.requests.DownloadFileStream
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.ByteReadChannelAllocator
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
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
        val fullUrl = urlsKeeper.createFileLinkUrl(it.filePath)

        ByteReadChannelAllocator {
            val scope = CoroutineScope(currentCoroutineContext() + SupervisorJob())
            val outChannel = ByteChannel()
            scope.launch {
                runCatchingSafely {
                    val response = client.get(fullUrl)
                    val channel: ByteReadChannel = response.bodyAsChannel()
                    channel.copyAndClose(outChannel)
                }
                scope.cancel()
            }
            outChannel
        } as T
    }
}
