package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.call
import io.ktor.client.request.accept
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod

abstract class AbstractRequestCallFactory : KtorCallFactory {
    private val methodsCache: MutableMap<String, String> = mutableMapOf()
    override suspend fun <T : Any> prepareCall(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ): HttpClientCall? {
        val preparedBody = prepareCallBody(client, baseUrl, request) ?: return null

        return client.call {
            url(
                methodsCache[request.method()] ?: "$baseUrl/${request.method()}".also {
                    methodsCache[request.method()] = it
                }
            )
            method = HttpMethod.Post
            accept(ContentType.Application.Json)

            body = preparedBody
        }
    }

    protected abstract fun <T : Any> prepareCallBody(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ): Any?
}