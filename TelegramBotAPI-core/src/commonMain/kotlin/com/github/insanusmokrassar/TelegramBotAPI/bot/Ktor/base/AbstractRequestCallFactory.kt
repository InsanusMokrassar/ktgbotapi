package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.newRequestException
import com.github.insanusmokrassar.TelegramBotAPI.requests.GetUpdates
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.types.RetryAfterError
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.timeout
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlin.collections.set

abstract class AbstractRequestCallFactory : KtorCallFactory {
    private val methodsCache: MutableMap<String, String> = mutableMapOf()
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? {
        val preparedBody = prepareCallBody(client, urlsKeeper, request) ?: return null

        client.post<HttpResponse> {
            url(
                methodsCache[request.method()] ?: "${urlsKeeper.commonAPIUrl}/${request.method()}".also {
                    methodsCache[request.method()] = it
                }
            )
            accept(ContentType.Application.Json)

            if (request is GetUpdates) {
                request.timeout?.times(1000L)?.let { customTimeoutMillis ->
                    if (customTimeoutMillis > 0) {
                        timeout {
                            requestTimeoutMillis = customTimeoutMillis
                            socketTimeoutMillis = customTimeoutMillis
                        }
                    }
                }
            }

            body = preparedBody
        }.let { response ->
            val content = response.receive<String>()
            val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)

            return (responseObject.result?.let {
                jsonFormatter.decodeFromJsonElement(request.resultDeserializer, it)
            } ?: responseObject.parameters?.let {
                val error = it.error
                if (error is RetryAfterError) {
                    delay(error.leftToRetry)
                    makeCall(client, urlsKeeper, request, jsonFormatter)
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

    protected abstract fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>
    ): Any?
}