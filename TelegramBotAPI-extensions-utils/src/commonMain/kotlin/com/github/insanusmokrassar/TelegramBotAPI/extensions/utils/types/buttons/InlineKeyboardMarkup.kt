package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.utils.flatMatrix

fun InlineKeyboardMarkup(
    vararg buttons: InlineKeyboardButton
): InlineKeyboardMarkup = InlineKeyboardMarkup(
    flatMatrix { buttons.forEach { +it } }
)
