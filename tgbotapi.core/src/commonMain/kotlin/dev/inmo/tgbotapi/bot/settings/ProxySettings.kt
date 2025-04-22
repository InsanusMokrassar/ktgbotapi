package dev.inmo.tgbotapi.bot.settings

import kotlinx.serialization.Serializable

@Serializable
data class ProxySettings(
    val host: String = "localhost",
    val port: Int = 1080,
    val username: String? = null,
    val password: String? = null,
)
