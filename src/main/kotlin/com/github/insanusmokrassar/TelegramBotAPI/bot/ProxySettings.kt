package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.useWith
import okhttp3.OkHttpClient

@Deprecated(
    "Replaced in settings package",
    ReplaceWith("ProxySettings", "com.github.insanusmokrassar.TelegramBotAPI.bot.settings.ProxySettings")
)
typealias ProxySettings = com.github.insanusmokrassar.TelegramBotAPI.bot.settings.ProxySettings


@Deprecated(
    "Replaced in Ktor package",
    ReplaceWith("useWith", "com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.useWith")
)
fun OkHttpClient.Builder.useWith(proxySettings: ProxySettings) = useWith(proxySettings)
