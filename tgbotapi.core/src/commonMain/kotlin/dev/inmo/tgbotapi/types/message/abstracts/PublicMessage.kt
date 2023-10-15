package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewPublicChat
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface PublicContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T> {
    override val chat: PreviewPublicChat
}
