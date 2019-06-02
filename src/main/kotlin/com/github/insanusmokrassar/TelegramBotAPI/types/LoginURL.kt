package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginURL(
    @SerialName(urlField)
    val url: String,
    @SerialName(forwardTextField)
    val forwardText: String? = null,
    @SerialName(botUsernameField)
    val botUsername: String? = null,
    @SerialName(requestWriteAccessField)
    val requestWriteAccess: Boolean? = null
)
