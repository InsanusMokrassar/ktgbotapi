package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup

interface WithReplyMarkup {
    val replyMarkup: KeyboardMarkup?
}
