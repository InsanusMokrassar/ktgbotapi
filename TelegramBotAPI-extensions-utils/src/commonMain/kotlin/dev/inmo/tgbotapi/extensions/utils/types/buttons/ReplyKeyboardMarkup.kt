package dev.inmo.tgbotapi.extensions.utils.types.buttons

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardButton
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.ReplyKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.utils.flatMatrix

fun ReplyKeyboardMarkup(
    vararg buttons: KeyboardButton,
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    selective: Boolean? = null
): ReplyKeyboardMarkup = ReplyKeyboardMarkup(
    flatMatrix { buttons.forEach { +it } },
    resizeKeyboard,
    oneTimeKeyboard,
    selective
)
