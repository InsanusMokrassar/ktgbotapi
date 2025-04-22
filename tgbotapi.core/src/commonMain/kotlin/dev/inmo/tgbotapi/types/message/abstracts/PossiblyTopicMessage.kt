package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicCreated

interface PossiblyTopicMessage : AccessibleMessage {
    val threadId: MessageThreadId?
    val threadCreatingInfo: ForumTopicCreated?
}
