package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup

interface ReplyMarkup {
    val replyMarkup: KeyboardMarkup?
}