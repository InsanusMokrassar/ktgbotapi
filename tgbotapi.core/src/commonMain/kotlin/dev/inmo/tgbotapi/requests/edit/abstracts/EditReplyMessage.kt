package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

interface EditReplyMessage : WithReplyMarkup {
    override val replyMarkup: InlineKeyboardMarkup?
}
