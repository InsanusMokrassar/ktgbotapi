package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat

interface ExtendedChat : Chat {
    val chatPhoto: ChatPhoto
}