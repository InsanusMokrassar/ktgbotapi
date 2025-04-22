package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.telegramBotAPIDefaultUrl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.ProxyConfig

/**
 * @param proxy Standard ktor [ProxyConfig]
 * @param ktorClientEngine Engine like [io.ktor.client.engine.cio.CIO]
 * @param ktorClientConfig Config block for preconfiguring of bot [HttpClient]
 */
public data class BotBuilder internal constructor(
    var proxy: ProxyConfig? = null,
    var ktorClientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>? = null,
    var ktorClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
) {
    internal fun createHttpClient(): HttpClient =
        ktorClientEngineFactory ?.let {
            HttpClient(
                it.create {
                    this@create.proxy = this@BotBuilder.proxy ?: this@create.proxy
                },
            ) {
                ktorClientConfig ?.let { it() }
            }
        } ?: HttpClient {
            ktorClientConfig ?.let { it() }
            engine {
                this@engine.proxy = this@BotBuilder.proxy ?: this@engine.proxy
            }
        }
}

/**
 * @return Created by [telegramBotWithCustomClientConfig] function [TelegramBot]. This executor will be preconfigured using [token] and
 * [block]
 */
public fun buildBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    block: BotBuilder.() -> Unit,
): TelegramBot =
    telegramBot(
        TelegramAPIUrlsKeeper(token, testServer, apiUrl),
        BotBuilder().apply(block).createHttpClient(),
    )
