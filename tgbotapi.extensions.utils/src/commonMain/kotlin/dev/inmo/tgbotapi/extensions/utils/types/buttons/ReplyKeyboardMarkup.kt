package dev.inmo.tgbotapi.extensions.utils.types.buttons

import dev.inmo.tgbotapi.types.buttons.KeyboardButton
import dev.inmo.tgbotapi.types.buttons.ReplyKeyboardMarkup
import dev.inmo.tgbotapi.utils.flatMatrix

fun ReplyKeyboardMarkup(
    vararg buttons: KeyboardButton,
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null
): ReplyKeyboardMarkup = ReplyKeyboardMarkup(
    flatMatrix { buttons.forEach { +it } },
    resizeKeyboard,
    oneTimeKeyboard,
    inputFieldPlaceholder,
    selective
)
