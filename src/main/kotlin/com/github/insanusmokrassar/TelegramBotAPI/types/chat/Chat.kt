package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto

interface Chat {
    val id: ChatId
    val chatPhoto: ChatPhoto?
}