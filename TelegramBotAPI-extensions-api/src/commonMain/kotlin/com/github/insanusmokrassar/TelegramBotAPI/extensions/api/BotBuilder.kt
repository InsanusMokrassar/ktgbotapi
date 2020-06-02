package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
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
    @Deprecated("ktorClientEngineFactory parameter will be used preferable. In future this parameter will be removed")
    var ktorClientEngine: HttpClientEngine? = null,
    var ktorClientEngineFactory: HttpClientEngineFactory<out HttpClientEngineConfig>? = null,
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
    } ?: ktorClientEngine ?.let { engine ->
        HttpClient(engine) {
            ktorClientConfig ?.let { it() }
            engine {
                this@engine.proxy = this@BotBuilder.proxy ?: this@engine.proxy
            }
        }
    } ?: HttpClient {
        ktorClientConfig ?.let { it() }
        engine {
            this@engine.proxy = this@BotBuilder.proxy ?: this@engine.proxy
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
