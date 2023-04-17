package dev.inmo.tgbotapi.bot.ktor

import dev.inmo.tgbotapi.bot.BaseRequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.ktor.base.*
import dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.utils.*
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

@RiskFeature
fun createTelegramBotDefaultKtorCallRequestsFactories() = listOf(
    SimpleRequestCallFactory(),
    MultipartRequestCallFactory(),
    DownloadFileRequestCallFactory,
    DownloadFileChannelRequestCallFactory
)

class KtorRequestsExecutorBuilder(
    var telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
) {
    var client: HttpClient = HttpClient()
    var callsFactories: List<KtorCallFactory> = emptyList()
    var excludeDefaultFactories: Boolean = false
    var requestsLimiter: RequestLimiter = ExceptionsOnlyLimiter
    var jsonFormatter: Json = nonstrictJsonFormat

    fun build() = KtorRequestsExecutor(
        telegramAPIUrlsKeeper,
        client,
        callsFactories,
        excludeDefaultFactories,
        requestsLimiter,
        jsonFormatter
    )
}

inline fun telegramBot(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = KtorRequestsExecutorBuilder(telegramAPIUrlsKeeper).apply(builder).build()

/**
 * Shortcut for [telegramBot]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token, testServer, apiUrl), builder)

