package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

interface PossiblyMarkedUp {
    val replyMarkup: InlineKeyboardMarkup?
}