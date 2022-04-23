package dev.inmo.tgbotapi.CommonAbstracts.types

import dev.inmo.tgbotapi.types.ChatIdentifier

interface ChatRequest : OptionalChatRequest {
    override val chatId: ChatIdentifier
}
