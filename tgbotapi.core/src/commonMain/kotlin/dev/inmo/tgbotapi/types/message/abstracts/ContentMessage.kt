package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

interface ContentMessage<T: MessageContent>: Message {
    val hasProtectedContent: Boolean
    val content: T

    val forwardable: Boolean
        get() = !hasProtectedContent
}
