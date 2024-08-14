package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChannelChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface ChannelContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, SignedMessage, WithSenderChatMessage, OptionallyFromUserMessage {
    override val chat: PreviewChannelChat
    override val from: User?
        get() = senderChat as? User
}
