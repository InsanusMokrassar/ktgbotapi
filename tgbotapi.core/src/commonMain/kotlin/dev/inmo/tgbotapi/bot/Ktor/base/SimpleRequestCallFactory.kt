package dev.inmo.tgbotapi.bot.Ktor.base

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

class SimpleRequestCallFactory : AbstractRequestCallFactory() {
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
