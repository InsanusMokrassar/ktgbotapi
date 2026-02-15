package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface PrivateForumContentMessage<T: MessageContent> : PrivateContentMessage<T>, PossiblyTopicMessage {
    override val threadId: MessageThreadId
}
