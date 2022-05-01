package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.abstracts.types.ReplyMarkup
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

interface EditReplyMessage : ReplyMarkup {
    override val replyMarkup: InlineKeyboardMarkup?
}
