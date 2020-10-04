package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

interface ContentMessage<T: MessageContent>: Message {
    val content: T
}
