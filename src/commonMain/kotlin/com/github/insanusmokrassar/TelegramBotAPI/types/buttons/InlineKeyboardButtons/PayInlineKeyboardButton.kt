package com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons

import kotlinx.serialization.Serializable

//TODO:: add check that this button first in a row (it MUST be first in a row)
@Serializable
data class PayInlineKeyboardButton(
    override val text: String,
    val pay: Boolean
) : InlineKeyboardButton
