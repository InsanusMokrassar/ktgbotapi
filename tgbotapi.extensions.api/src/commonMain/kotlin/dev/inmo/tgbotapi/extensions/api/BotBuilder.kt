package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.*

/**
 * @param proxy Standard ktor [ProxyConfig]
 * @param ktorClientEngine Engine like [io.ktor.client.engine.cio.CIO]
 * @param ktorClientConfig Config block for preconfiguring of bot [HttpClient]
 */
data class BotBuilder internal constructor(
    var proxy: ProxyConfig? = null,
    var ktorClientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>? = null,
    var ktorClientConfig: (HttpClientConfig<*>.() -> Unit) ? = null
) {
    internal fun createHttpClient(): HttpClient = ktorClientEngineFactory ?.let {
        HttpClient(
            it.create {
                this@create.proxy = this@BotBuilder.proxy ?: this@create.proxy
            }
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
fun telegramBot(
    token: String,
    block: BotBuilder.() -> Unit
): TelegramBot = telegramBot(
    TelegramAPIUrlsKeeper(token),
    BotBuilder().apply(block).createHttpClient()
)
