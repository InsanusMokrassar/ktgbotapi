package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.call
import io.ktor.client.request.accept
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.content.TextContent

class SimpleRequestCallFactory : KtorCallFactory {
    override suspend fun <T: Any> prepareCall(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ): HttpClientCall? = (request as? SimpleRequest<T>) ?.let {
        castedRequest ->
        client.call {
            url("$baseUrl/${castedRequest.method()}")
            method = HttpMethod.Post
            accept(ContentType.Application.Json)

            val content = request.toJsonWithoutNulls().toString()

            body = TextContent(
                content,
                ContentType.Application.Json
            )
            build()
        }
    }
}