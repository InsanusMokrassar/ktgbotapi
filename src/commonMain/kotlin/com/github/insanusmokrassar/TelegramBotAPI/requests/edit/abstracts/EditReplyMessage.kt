package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ReplyMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

interface EditReplyMessage : ReplyMarkup {
    override val replyMarkup: InlineKeyboardMarkup?
}