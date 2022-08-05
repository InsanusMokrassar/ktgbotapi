package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup

interface WithReplyMarkup {
    val replyMarkup: KeyboardMarkup?
}
@Deprecated("Renamed", ReplaceWith("WithReplyMarkup", "dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup"))
typealias ReplyMarkup = WithReplyMarkup
