package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor

import com.github.insanusmokrassar.TelegramBotAPI.bot.BaseRequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base.*
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.newRequestException
import com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters.EmptyLimiter
import com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters.RequestLimiter
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.utils.*
import io.ktor.client.HttpClient
import io.ktor.client.features.*
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.json.Json

class KtorRequestsExecutor(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient(),
    callsFactories: List<KtorCallFactory> = emptyList(),
    excludeDefaultFactories: Boolean = false,
    private val requestsLimiter: RequestLimiter = EmptyLimiter,
    private val jsonFormatter: Json = nonstrictJsonFormat
) : BaseRequestsExecutor(telegramAPIUrlsKeeper) {
    private val callsFactories: List<KtorCallFactory> = callsFactories.run {
        if (!excludeDefaultFactories) {
            this + listOf(SimpleRequestCallFactory, MultipartRequestCallFactory, DownloadFileRequestCallFactory)
        } else {
            this
        }
    }

    private val client = client.config {
        if (client.feature(HttpTimeout) == null) {
            install(HttpTimeout)
        }
    }

    override suspend fun <T : Any> execute(request: Request<T>): T {
        return handleSafely(
            { e ->
                throw if (e is ClientRequestException) {
                    val content = e.response ?.readText() ?: throw e
                    val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)
                    newRequestException(
                        responseObject,
                        content,
                        "Can't get result object from $content"
                    )
                } else {
                    e
                }
            }
        ) {
            requestsLimiter.limit {
                var result: T? = null
                for (potentialFactory in callsFactories) {
                    result = potentialFactory.makeCall(
                        client,
                        telegramAPIUrlsKeeper,
                        request,
                        jsonFormatter
                    )
                    if (result != null) {
                        break
                    }
                }

                result ?: error("Can't execute request: $request")
            }
        }
    }

    override fun close() {
        client.close()
    }
}
