package com.github.insanusmokrassar.TelegramBotAPI.bot.settings

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class ProxySettings(
    @Optional
    val host: String = "localhost",
    @Optional
    val port: Int = 1080,
    @Optional
    val username: String? = null,
    @Optional
    val password: String? = null
)
