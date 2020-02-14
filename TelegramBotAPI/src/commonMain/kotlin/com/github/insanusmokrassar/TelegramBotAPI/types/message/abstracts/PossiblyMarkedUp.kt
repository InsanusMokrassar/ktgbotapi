package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

interface PossiblyMarkedUp {
    val replyMarkup: InlineKeyboardMarkup?
}