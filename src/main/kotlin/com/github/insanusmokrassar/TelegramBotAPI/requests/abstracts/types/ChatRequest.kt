package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier

interface ChatRequest {
    val chatId: ChatIdentifier
}