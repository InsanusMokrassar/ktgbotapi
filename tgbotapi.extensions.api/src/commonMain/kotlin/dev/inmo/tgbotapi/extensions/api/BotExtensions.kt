package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutorBuilder
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.telegramBotAPIDefaultUrl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.*

/**
 * Allows to create bot using bot [urlsKeeper]
 */
@Deprecated("Replaced in core", ReplaceWith("telegramBot", "dev.inmo.tgbotapi.bot.Ktor.telegramBot"))
fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper
): TelegramBot = dev.inmo.tgbotapi.bot.Ktor.telegramBot(
    urlsKeeper
)

/**
 * Allows to create bot using bot [urlsKeeper] and already prepared [client]
 */
fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient
): TelegramBot = dev.inmo.tgbotapi.bot.Ktor.telegramBot(urlsKeeper) {
    this.client = client
}

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngineFactory] by passing [clientFactory] param and optionally
 * configure it with [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T: HttpClientEngineConfig> telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientFactory: HttpClientEngineFactory<T>,
    noinline clientConfig: HttpClientConfig<T>.() -> Unit = {}
) = telegramBot(
    urlsKeeper,
    HttpClient(clientFactory, clientConfig)
)

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientEngine: HttpClientEngine,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit = {}
) = telegramBot(
    urlsKeeper,
    HttpClient(clientEngine, clientConfig)
)

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngine] by configuring [HttpClient] using
 * [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit
) = telegramBot(
    urlsKeeper,
    HttpClient(clientConfig)
)

/**
 * Allows to create bot using bot [token], [apiUrl] (for custom api servers) and already prepared [client]
 */
@Deprecated("Replaced in core", ReplaceWith("telegramBot", "dev.inmo.tgbotapi.bot.Ktor.telegramBot"))
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl
): TelegramBot = dev.inmo.tgbotapi.bot.Ktor.telegramBot(token, apiUrl)

/**
 * Allows to create bot using bot [token], [apiUrl] (for custom api servers) and already prepared [client]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    client: HttpClient
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token, apiUrl), client)

@Suppress("NOTHING_TO_INLINE")
inline fun <T: HttpClientEngineConfig> telegramBot(
    token: String,
    clientFactory: HttpClientEngineFactory<T>,
    apiUrl: String = telegramBotAPIDefaultUrl,
    noinline clientConfig: HttpClientConfig<T>.() -> Unit = {}
) = telegramBot(
    TelegramAPIUrlsKeeper(token, apiUrl),
    clientFactory,
    clientConfig
)

/**
 * Allows to create bot using bot [token] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    token: String,
    clientEngine: HttpClientEngine,
    apiUrl: String = telegramBotAPIDefaultUrl,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit = {}
) = telegramBot(
    TelegramAPIUrlsKeeper(token, apiUrl),
    clientEngine,
    clientConfig
)

/**
 * Allows to create bot using bot [token] and [apiUrl] and specify [HttpClientEngine] by configuring [HttpClient] using
 * [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit
) = telegramBot(
    TelegramAPIUrlsKeeper(token, apiUrl),
    clientConfig
)

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
@Deprecated("Will be removed in next releases", ReplaceWith("telegramBot", "dev.inmo.tgbotapi.extensions.api.telegramBot"))
fun telegramBotWithCustomClientConfig(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientEngine: HttpClientEngine,
    clientConfig: HttpClientConfig<*>.() -> Unit
): TelegramBot = telegramBot(
    urlsKeeper,
    HttpClient(clientEngine, clientConfig)
)

/**
 * Allows to create bot using bot [urlsKeeper] and optionally configure [HttpClient] using [clientConfig]
 */
@Deprecated("Will be removed in next releases", ReplaceWith("telegramBot", "dev.inmo.tgbotapi.extensions.api.telegramBot"))
fun telegramBotWithCustomClientConfig(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientConfig: HttpClientConfig<*>.() -> Unit
): TelegramBot = telegramBot(
    urlsKeeper,
    HttpClient(clientConfig)
)

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Renamed", ReplaceWith("telegramBot", "dev.inmo.tgbotapi.extensions.api.telegramBot"))
inline fun telegramBotWithCustomClientConfig(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit
) = telegramBot(token, apiUrl = apiUrl, clientConfig = clientConfig)
