package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ProxyConfig

/**
 * @param proxy Standard ktor [ProxyConfig]
 * @param ktorClientEngine Engine like [io.ktor.client.engine.cio.CIO]
 * @param ktorClientConfig Config block for preconfiguring of bot [HttpClient]
 */
data class BotBuilder internal constructor(
    var proxy: ProxyConfig? = null,
    var ktorClientEngine: HttpClientEngine? = null,
    var ktorClientConfig: (HttpClientConfig<*>.() -> Unit) ? = null
) {
    internal fun createHttpClient(): HttpClient = ktorClientEngine ?.let { engine ->
        HttpClient(engine) {
            ktorClientConfig ?.let { it() }
            engine {
                proxy = this@BotBuilder.proxy ?: proxy
            }
        }
    } ?: HttpClient {
        ktorClientConfig ?.let { it() }
        engine {
            proxy = this@BotBuilder.proxy ?: proxy
        }
    }
}

/**
 * @return Created by [telegramBotWithCustomClientConfig] function [RequestsExecutor]. This executor will be preconfigured using [token] and
 * [block]
 */
fun telegramBot(
    token: String,
    block: BotBuilder.() -> Unit
): RequestsExecutor = telegramBot(
    TelegramAPIUrlsKeeper(token),
    BotBuilder().apply(block).createHttpClient()
)
