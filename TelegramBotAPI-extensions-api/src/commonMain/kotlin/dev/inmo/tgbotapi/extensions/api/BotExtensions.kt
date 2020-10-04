package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine

/**
 * Allows to create bot using bot [urlsKeeper] and already prepared [client]
 */
fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient()
): TelegramBot = KtorRequestsExecutor(
    urlsKeeper,
    client
)

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
fun telegramBotWithCustomClientConfig(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientEngine: HttpClientEngine,
    clientConfig: HttpClientConfig<*>.() -> Unit = {}
): TelegramBot = telegramBot(
    urlsKeeper,
    HttpClient(clientEngine, clientConfig)
)

/**
 * Allows to create bot using bot [urlsKeeper] and optionally configure [HttpClient] using [clientConfig]
 */
fun telegramBotWithCustomClientConfig(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientConfig: HttpClientConfig<*>.() -> Unit = {}
): TelegramBot = telegramBot(
    urlsKeeper,
    HttpClient(clientConfig)
)

/**
 * Allows to create bot using bot [token]
 */
fun telegramBot(
    token: String
): TelegramBot = telegramBotWithCustomClientConfig(TelegramAPIUrlsKeeper(token))

/**
 * Allows to create bot using bot [token] and already prepared [client]
 */
fun telegramBot(
    token: String,
    client: HttpClient
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token), client)

/**
 * Allows to create bot using bot [token] and configure [HttpClient] using [clientConfig]
 */
fun telegramBotWithCustomClientConfig(
    token: String,
    clientConfig: HttpClientConfig<*>.() -> Unit
): TelegramBot = telegramBotWithCustomClientConfig(TelegramAPIUrlsKeeper(token), clientConfig)

/**
 * Allows to create bot using bot [token] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
fun telegramBot(
    token: String,
    clientEngine: HttpClientEngine,
    clientConfig: HttpClientConfig<*>.() -> Unit = {}
): TelegramBot = telegramBotWithCustomClientConfig(TelegramAPIUrlsKeeper(token), clientEngine, clientConfig)
