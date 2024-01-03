package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.MessageId

interface MessagesAction: ChatRequest {
    val messageIds: List<MessageId>
}
