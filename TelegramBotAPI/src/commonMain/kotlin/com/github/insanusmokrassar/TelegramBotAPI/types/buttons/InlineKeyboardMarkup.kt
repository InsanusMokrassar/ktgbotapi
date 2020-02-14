package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import com.github.insanusmokrassar.TelegramBotAPI.types.inlineKeyboardField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineKeyboardMarkup(
    @SerialName(inlineKeyboardField)
    val keyboard: Matrix<InlineKeyboardButton>
) : KeyboardMarkup
