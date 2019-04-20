package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import kotlinx.serialization.*

@Serializable(InlineKeyboardButtonSerializer::class)
interface InlineKeyboardButton {
    val text: String
}

object InlineKeyboardButtonSerializer : KSerializer<InlineKeyboardButton> by ContextSerializer(InlineKeyboardButton::class)
