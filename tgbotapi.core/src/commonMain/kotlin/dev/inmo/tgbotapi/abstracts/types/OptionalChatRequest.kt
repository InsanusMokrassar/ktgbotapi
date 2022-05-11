package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.ChatIdentifier

interface OptionalChatRequest {
    val chatId: ChatIdentifier?
}
