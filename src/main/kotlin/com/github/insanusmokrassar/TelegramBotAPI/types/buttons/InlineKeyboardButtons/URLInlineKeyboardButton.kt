package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import kotlinx.serialization.Serializable

@Serializable
data class URLInlineKeyboardButton(
    override val text: String,
    val url: String
) : InlineKeyboardButton
