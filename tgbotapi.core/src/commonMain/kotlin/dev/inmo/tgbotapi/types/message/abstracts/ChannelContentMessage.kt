package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.chat.PreviewChannelChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface ChannelContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, SignedMessage, WithSenderChatMessage {
    override val chat: PreviewChannelChat
    override val senderChat: PreviewChannelChat
        get() = chat
}
