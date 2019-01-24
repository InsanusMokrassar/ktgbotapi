package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.useWith
import okhttp3.OkHttpClient

data class ProxySettings(
    val host: String = "localhost",
    val port: Int = 1080,
    val username: String? = null,
    val password: String? = null
)


@Deprecated(
    "Replaced in Ktor package",
    ReplaceWith("useWith", "com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.useWith")
)
fun OkHttpClient.Builder.useWith(proxySettings: ProxySettings) = useWith(proxySettings)
