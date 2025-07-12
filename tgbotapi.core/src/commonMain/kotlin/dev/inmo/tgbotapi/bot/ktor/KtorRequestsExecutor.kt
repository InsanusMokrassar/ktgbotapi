package dev.inmo.tgbotapi.bot.ktor

import dev.inmo.kslog.common.KSLog
import dev.inmo.tgbotapi.bot.BaseRequestsExecutor
import dev.inmo.tgbotapi.bot.ktor.middlewares.TelegramBotMiddlewaresPipelinesHandler
import dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import io.ktor.client.*
import kotlinx.serialization.json.Json

/**
 * Represents default [BaseRequestsExecutor] working on [Ktor](https://ktor.io) [HttpClient]
 *
 * * On JS, JVM and MingwX64 platforms it is [dev.inmo.tgbotapi.bot.ktor.base.DefaultKtorRequestsExecutor]
 * * On LinuxX64 it is [dev.inmo.tgbotapi.bot.ktor.base.MultipleClientKtorRequestsExecutor]
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KtorRequestsExecutor internal constructor(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient,
    callsFactories: List<KtorCallFactory>,
    excludeDefaultFactories: Boolean,
    requestsLimiter: RequestLimiter,
    jsonFormatter: Json,
    pipelineStepsHolder: TelegramBotPipelinesHandler,
    logger: KSLog,
    diff: Unit // just a diff property to know where constructor and where calling function with defaults
) : BaseRequestsExecutor {
    override val Log: KSLog
    override suspend fun <T : Any> execute(request: Request<T>): T
    override fun close()
}

fun KtorRequestsExecutor(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient(),
    callsFactories: List<KtorCallFactory> = emptyList(),
    excludeDefaultFactories: Boolean = false,
    requestsLimiter: RequestLimiter = ExceptionsOnlyLimiter,
    jsonFormatter: Json = nonstrictJsonFormat,
    pipelineStepsHolder: TelegramBotPipelinesHandler = TelegramBotMiddlewaresPipelinesHandler(),
    logger: KSLog = DefaultKTgBotAPIKSLog,
) = KtorRequestsExecutor(
    telegramAPIUrlsKeeper = telegramAPIUrlsKeeper,
    client = client,
    callsFactories = callsFactories,
    excludeDefaultFactories = excludeDefaultFactories,
    requestsLimiter = requestsLimiter,
    jsonFormatter = jsonFormatter,
    pipelineStepsHolder = pipelineStepsHolder,
    logger = logger,
    diff = Unit,
)
