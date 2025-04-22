package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.telegramBotAPIDefaultUrl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.*

/**
 * Allows to create bot using bot [urlsKeeper] and already prepared [client]
 */
public fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient(),
): TelegramBot =
    telegramBot(urlsKeeper) {
        this.client = client
    }

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngineFactory] by passing [clientFactory] param and optionally
 * configure it with [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun <T : HttpClientEngineConfig> telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientFactory: HttpClientEngineFactory<T>,
    noinline clientConfig: HttpClientConfig<T>.() -> Unit = {},
): TelegramBot =
    telegramBot(
        urlsKeeper,
        HttpClient(clientFactory, clientConfig),
    )

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    clientEngine: HttpClientEngine,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit = {},
): TelegramBot =
    telegramBot(
        urlsKeeper,
        HttpClient(clientEngine, clientConfig),
    )

/**
 * Allows to create bot using bot [urlsKeeper] and specify [HttpClientEngine] by configuring [HttpClient] using
 * [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun telegramBot(
    urlsKeeper: TelegramAPIUrlsKeeper,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit,
): TelegramBot =
    telegramBot(
        urlsKeeper,
        HttpClient(clientConfig),
    )

/**
 * Allows to create bot using bot [token], [apiUrl] (for custom api servers) and already prepared [client]
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    client: HttpClient = HttpClient(),
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token, testServer, apiUrl), client)

@Suppress("NOTHING_TO_INLINE")
public inline fun <T : HttpClientEngineConfig> telegramBot(
    token: String,
    clientFactory: HttpClientEngineFactory<T>,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    noinline clientConfig: HttpClientConfig<T>.() -> Unit = {},
): TelegramBot =
    telegramBot(
        TelegramAPIUrlsKeeper(token, testServer, apiUrl),
        clientFactory,
        clientConfig,
    )

/**
 * Allows to create bot using bot [token] and specify [HttpClientEngine] by passing [clientEngine] param and optionally
 * configure [HttpClient] using [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun telegramBot(
    token: String,
    clientEngine: HttpClientEngine,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit = {},
): TelegramBot =
    telegramBot(
        TelegramAPIUrlsKeeper(token, testServer, apiUrl),
        clientEngine,
        clientConfig,
    )

/**
 * Allows to create bot using bot [token] and [apiUrl] and specify [HttpClientEngine] by configuring [HttpClient] using
 * [clientConfig]
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    noinline clientConfig: HttpClientConfig<*>.() -> Unit,
): TelegramBot =
    telegramBot(
        TelegramAPIUrlsKeeper(token, testServer, apiUrl),
        clientConfig,
    )
