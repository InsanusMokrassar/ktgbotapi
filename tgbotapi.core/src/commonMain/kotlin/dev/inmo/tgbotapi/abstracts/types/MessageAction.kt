package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.MessageId

interface MessageAction: ChatRequest {
    val messageId: MessageId
}
