package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

@Serializable(InlineKeyboardButtonSerializer::class)
sealed class InlineKeyboardButton {
    abstract val text: String
}

//TODO:: add check that this button first in a row (it MUST be first in a row)
@Serializable
data class PayInlineKeyboardButton(
    override val text: String,
    val pay: Boolean
) : InlineKeyboardButton()

@Serializable
data class CallbackDataInlineKeyboardButton(
    @SerialName(textField)
    override val text: String,
    @SerialName(callbackDataField)
    val callbackData: String
) : InlineKeyboardButton()

@Serializable
data class LoginURLInlineKeyboardButton(
    override val text: String,
    @SerialName(loginUrlField)
    val loginUrl: LoginURL
) : InlineKeyboardButton()

@Serializable
data class SwitchInlineQueryCurrentChatInlineKeyboardButton(
    override val text: String,
    @SerialName(switchInlineQueryCurrentChatField)
    val switchInlineQueryCurrentChat: String
) : InlineKeyboardButton()

@Serializable
data class SwitchInlineQueryInlineKeyboardButton(
    override val text: String,
    @SerialName("switch_inline_query")
    val switchInlineQuery: String
) : InlineKeyboardButton()

@Serializable
data class URLInlineKeyboardButton(
    override val text: String,
    val url: String
) : InlineKeyboardButton()
