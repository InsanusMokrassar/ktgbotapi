package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.games.CallbackGame
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable(InlineKeyboardButtonSerializer::class)
sealed class InlineKeyboardButton {
    abstract val text: String
}

@Serializable
data class UnknownInlineKeyboardButton internal constructor(
    override val text: String,
    val rawData: JsonElement
) : InlineKeyboardButton()

@Serializable
data class PayInlineKeyboardButton(
    override val text: String,
    @SerialName(payField)
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
data class CallbackGameInlineKeyboardButton(
    @SerialName(textField)
    override val text: String
) : InlineKeyboardButton() {
    @SerialName(callbackGameField)
    private val callbackGame = CallbackGame
}

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
    @SerialName(switchInlineQueryField)
    val switchInlineQuery: String
) : InlineKeyboardButton()

@Serializable
data class URLInlineKeyboardButton(
    override val text: String,
    @SerialName(urlField)
    val url: String
) : InlineKeyboardButton()
