package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.ChatIdentifier

interface ChatRequest : OptionalChatRequest {
    override val chatId: ChatIdentifier
}
