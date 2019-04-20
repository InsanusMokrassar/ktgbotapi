package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer

@Serializable
data class InlineKeyboardMarkup(
    @SerialName("inline_keyboard")
    val keyboard: Matrix<InlineKeyboardButton>
) : KeyboardMarkup
