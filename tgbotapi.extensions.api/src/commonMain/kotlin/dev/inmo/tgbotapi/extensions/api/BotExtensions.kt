package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.telegramBotAPIDefaultUrl
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
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl
): TelegramBot = telegramBotWithCustomClientConfig(TelegramAPIUrlsKeeper(token, apiUrl))

/**
 * Allows to create bot using bot [token] and already prepared [client]
 */
fun telegramBot(
    token: String,
    client: HttpClient,
    apiUrl: String = telegramBotAPIDefaultUrl
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token, apiUrl), client)

/**
 * Allows to create bot using bot [token] and configure [HttpClient] using [clientConfig]
 */
fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    clientConfig: HttpClientConfig<*>.() -> Unit
): TelegramBot = telegramBotWithCustomClientConfig(TelegramAPIUrlsKeeper(token, apiUrl), clientConfig)

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Renamed", ReplaceWith("telegramBot", "dev.inmo.tgbotapi.extensions.api.telegramBot"))
inline fun telegramBotWithCustomClientConfig(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit
) = telegramBot(token, apiUrl, clientConfig)

/**
 * Allows to create bot using bot [token] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
fun telegramBot(
    token: String,
    clientEngine: HttpClientEngine,
    apiUrl: String = telegramBotAPIDefaultUrl,
    clientConfig: HttpClientConfig<*>.() -> Unit = {}
): TelegramBot = telegramBotWithCustomClientConfig(TelegramAPIUrlsKeeper(token, apiUrl), clientEngine, clientConfig)
