package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface BusinessContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, FromUserMessage {
    override val chat: PreviewPrivateChat
    override val businessConnectionId: BusinessConnectionId
}