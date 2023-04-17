package dev.inmo.tgbotapi.bot.ktor

import dev.inmo.tgbotapi.bot.BaseRequestsExecutor
import dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import io.ktor.client.*
import kotlinx.serialization.json.Json

/**
 * Represents default [BaseRequestsExecutor] working on [Ktor](https://ktor.io) [HttpClient]
 *
 * * On JS and JVM platforms it is [dev.inmo.tgbotapi.bot.ktor.base.DefaultKtorRequestsExecutor]
 * * On LinuxX64 and MingwX64 it is [dev.inmo.tgbotapi.bot.ktor.base.MultipleClientKtorRequestsExecutor]
 */
expect class KtorRequestsExecutor (
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient(),
    callsFactories: List<KtorCallFactory> = emptyList(),
    excludeDefaultFactories: Boolean = false,
    requestsLimiter: RequestLimiter = ExceptionsOnlyLimiter,
    jsonFormatter: Json = nonstrictJsonFormat,
    pipelineStepsHolder: KtorPipelineStepsHolder = KtorPipelineStepsHolder
) : BaseRequestsExecutor
