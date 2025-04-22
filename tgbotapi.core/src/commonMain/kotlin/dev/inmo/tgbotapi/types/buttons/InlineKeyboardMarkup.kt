package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton
import dev.inmo.tgbotapi.types.inlineKeyboardField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineKeyboardMarkup(
    @SerialName(inlineKeyboardField)
    val keyboard: Matrix<InlineKeyboardButton>,
) : KeyboardMarkup {
    init {
        val isTherePayButton =
            keyboard.any { it ->
                it.any {
                    it is PayInlineKeyboardButton
                }
            }
        if (isTherePayButton) {
            // first button is not PayInlineKeyboardButton
            val firstIsPaymentButton = keyboard.first().firstOrNull() is PayInlineKeyboardButton
            if (!firstIsPaymentButton) {
                error("In case if PayInlineKeyboardButton included in keyboard - it must be the first one")
            }
        }
    }

    operator fun plus(other: InlineKeyboardMarkup): InlineKeyboardMarkup {
        return InlineKeyboardMarkup(
            keyboard + other.keyboard,
        )
    }

    operator fun minus(other: InlineKeyboardMarkup): InlineKeyboardMarkup {
        val otherButtons = other.keyboard.flatten()
        return InlineKeyboardMarkup(
            keyboard.mapNotNull { row ->
                row.filter { button ->
                    button !in otherButtons
                }.takeIf {
                    it.isNotEmpty()
                }
            },
        )
    }
}
