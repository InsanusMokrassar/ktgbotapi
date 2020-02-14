package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier

interface MessageAction: ChatRequest {
    val messageId: MessageIdentifier
}