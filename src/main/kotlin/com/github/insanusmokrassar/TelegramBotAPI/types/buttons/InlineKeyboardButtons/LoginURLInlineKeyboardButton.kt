package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginURLInlineKeyboardButton(
    override val text: String,
    @SerialName(loginUrlField)
    val loginUrl: LoginURL
) : InlineKeyboardButton

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
