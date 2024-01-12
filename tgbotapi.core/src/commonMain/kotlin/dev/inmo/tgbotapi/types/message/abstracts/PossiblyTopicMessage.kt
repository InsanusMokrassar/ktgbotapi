package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.MessageThreadId

interface PossiblyTopicMessage : AccessibleMessage {
    val threadId: MessageThreadId?
}
