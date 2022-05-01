package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface ChannelContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, SignedMessage, WithSenderChatMessage {
    override val chat: ChannelChat
    override val senderChat: ChannelChat
        get() = chat
}
