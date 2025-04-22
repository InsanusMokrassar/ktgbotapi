package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.kslog.common.KSLog
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

class SimpleRequestCallFactory(logger: KSLog? = null) : AbstractRequestCallFactory(logger ?: DefaultKTgBotAPIKSLog) {
    override fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
    ): Any? =
        (request as? SimpleRequest<T>) ?.let { _ ->
            val content = request.json().toString()

            TextContent(
                content,
                ContentType.Application.Json,
            )
        }
}
