package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface PrivateContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, FromUserMessage {
    override val chat: PreviewPrivateChat
}
