package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import com.github.insanusmokrassar.TelegramBotAPI.types.switchInlineQueryCurrentChatField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SwitchInlineQueryCurrentChatInlineKeyboardButton(
    override val text: String,
    @SerialName(switchInlineQueryCurrentChatField)
    val switchInlineQueryCurrentChat: String
) : InlineKeyboardButton
