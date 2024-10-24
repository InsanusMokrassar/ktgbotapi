package dev.inmo.tgbotapi.bot.ktor

import dev.inmo.kslog.common.KSLog
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.ktor.base.*
import dev.inmo.tgbotapi.bot.ktor.middlewares.TelegramBotMiddlewaresPipelinesHandler
import dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.utils.*
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

@RiskFeature
fun createTelegramBotDefaultKtorCallRequestsFactories(logger: KSLog? = null) = listOf(
    SimpleRequestCallFactory(logger),
    MultipartRequestCallFactory(logger),
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
    var logger: KSLog = DefaultKTgBotAPIKSLog
    var pipelineStepsHolder: TelegramBotPipelinesHandler = TelegramBotMiddlewaresPipelinesHandler()

    fun includeMiddlewares(block: TelegramBotMiddlewaresPipelinesHandler.Builder.() -> Unit) {
        pipelineStepsHolder = TelegramBotMiddlewaresPipelinesHandler.build(block)
    }

    fun build() = KtorRequestsExecutor(
        telegramAPIUrlsKeeper,
        client,
        callsFactories,
        excludeDefaultFactories,
        requestsLimiter,
        jsonFormatter,
        pipelineStepsHolder,
        logger
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

