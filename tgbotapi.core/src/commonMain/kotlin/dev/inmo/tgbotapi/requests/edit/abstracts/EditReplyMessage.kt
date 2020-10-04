package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.ReplyMarkup
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

interface EditReplyMessage : ReplyMarkup {
    override val replyMarkup: InlineKeyboardMarkup?
}