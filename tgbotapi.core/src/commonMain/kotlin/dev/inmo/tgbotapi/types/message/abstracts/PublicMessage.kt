package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface PublicContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T> {
    override val chat: PublicChat
}
