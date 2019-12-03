package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier

interface ChatRequest {
    val chatId: ChatIdentifier
}