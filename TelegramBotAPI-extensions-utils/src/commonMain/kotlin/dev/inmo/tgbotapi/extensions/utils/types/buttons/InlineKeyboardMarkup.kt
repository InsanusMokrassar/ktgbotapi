package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.utils.flatMatrix

fun InlineKeyboardMarkup(
    vararg buttons: InlineKeyboardButton
): InlineKeyboardMarkup = InlineKeyboardMarkup(
    flatMatrix { buttons.forEach { +it } }
)
