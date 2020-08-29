package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

object SimpleRequestCallFactory : AbstractRequestCallFactory() {
    override fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>
    ): Any? = (request as? SimpleRequest<T>) ?.let { _ ->
        val content = request.json().toString()

        TextContent(
            content,
            ContentType.Application.Json
        )
    }
}