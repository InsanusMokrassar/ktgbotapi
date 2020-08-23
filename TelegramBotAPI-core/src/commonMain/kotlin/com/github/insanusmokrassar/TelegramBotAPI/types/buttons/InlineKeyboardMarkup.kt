package com.github.insanusmokrassar.TelegramBotAPI.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton
import com.github.insanusmokrassar.TelegramBotAPI.types.inlineKeyboardField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineKeyboardMarkup(
    @SerialName(inlineKeyboardField)
    val keyboard: Matrix<InlineKeyboardButton>
) : KeyboardMarkup {
    init {
        val isTherePayButton = keyboard.any { it ->
            it.any {
                it is PayInlineKeyboardButton
            }
        }
        if (isTherePayButton) {
            // first button is not PayInlineKeyboardButton
            val firstIsPaymentButton = keyboard.first().firstOrNull() is PayInlineKeyboardButton
            if (!firstIsPaymentButton) {
                error("In case if PayInlineKeyboardButton included in keyboard - it must ")
            }
        }
    }
}
