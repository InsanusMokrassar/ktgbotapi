package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup

interface ReplyMarkup {
    val replyMarkup: KeyboardMarkup?
}