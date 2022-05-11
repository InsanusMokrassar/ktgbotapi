package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.MessageIdentifier

interface MessageAction: ChatRequest {
    val messageId: MessageIdentifier
}
