package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import com.github.insanusmokrassar.TelegramBotAPI.types.callbackDataField
import com.github.insanusmokrassar.TelegramBotAPI.types.textField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CallbackDataInlineKeyboardButton(
    @SerialName(textField)
    override val text: String,
    @SerialName(callbackDataField)
    val callbackData: String
) : InlineKeyboardButton
