package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SwitchInlineQueryInlineKeyboardButton(
    override val text: String,
    @SerialName("switch_inline_query")
    val switchInlineQuery: String
) : InlineKeyboardButton
