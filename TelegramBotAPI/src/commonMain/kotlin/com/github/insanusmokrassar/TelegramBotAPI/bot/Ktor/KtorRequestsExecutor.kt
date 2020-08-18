package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor

import com.github.insanusmokrassar.TelegramBotAPI.bot.BaseRequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base.MultipartRequestCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base.SimpleRequestCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.newRequestException
import com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters.EmptyLimiter
import com.github.insanusmokrassar.TelegramBotAPI.bot.settings.limiters.RequestLimiter
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.types.RetryAfterError
import com.github.insanusmokrassar.TelegramBotAPI.utils.*
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.*
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.coroutines.delay
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
            asSequence().plus(SimpleRequestCallFactory()).plus(MultipartRequestCallFactory()).toList()
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
                    val content = e.response.readText()
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
                var statement: HttpStatement? = null
                for (factory in callsFactories) {
                    statement = factory.prepareCall(
                        client,
                        telegramAPIUrlsKeeper.commonAPIUrl,
                        request
                    )
                    if (statement != null) {
                        break
                    }
                }

                val response = statement?.execute() ?: throw IllegalArgumentException("Can't execute request: $request")
                val content = response.receive<String>()
                val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)

                (responseObject.result?.let {
                    jsonFormatter.decodeFromJsonElement(request.resultDeserializer, it)
                } ?: responseObject.parameters?.let {
                    val error = it.error
                    if (error is RetryAfterError) {
                        delay(error.leftToRetry)
                        execute(request)
                    } else {
                        null
                    }
                } ?: response.let {
                    throw newRequestException(
                        responseObject,
                        content,
                        "Can't get result object from $content"
                    )
                })
            }
        }
    }

    override fun close() {
        client.close()
    }
}
