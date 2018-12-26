package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*

interface Chat {
    val id: ChatId
    val chatPhoto: ChatPhoto?
}