package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.requests.GetUpdates
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import io.ktor.client.HttpClient
import io.ktor.client.features.timeout
import io.ktor.client.request.*
import io.ktor.client.statement.HttpStatement
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import kotlin.collections.set

abstract class AbstractRequestCallFactory : KtorCallFactory {
    private val methodsCache: MutableMap<String, String> = mutableMapOf()
    override suspend fun <T : Any> prepareCall(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ): HttpStatement? {
        val preparedBody = prepareCallBody(client, baseUrl, request) ?: return null

        return HttpStatement(
            HttpRequestBuilder().apply {
                url(
                    methodsCache[request.method()] ?: "$baseUrl/${request.method()}".also {
                        methodsCache[request.method()] = it
                    }
                )
                method = HttpMethod.Post
                accept(ContentType.Application.Json)

                if (request is GetUpdates) {
                    request.timeout ?.times(1000L) ?.let { customTimeoutMillis ->
                        if (customTimeoutMillis > 0) {
                            timeout {
                                requestTimeoutMillis = customTimeoutMillis
                                socketTimeoutMillis = customTimeoutMillis
                            }
                        }
                    }
                }

                body = preparedBody
            },
            client
        )
    }

    protected abstract fun <T : Any> prepareCallBody(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ): Any?
}