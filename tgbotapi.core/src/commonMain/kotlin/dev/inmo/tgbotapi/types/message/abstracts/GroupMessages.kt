package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.abstracts.*
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

interface GroupMessage<T : MessageContent> : PublicMessage<T> {
    override val chat: GroupChat
}

interface FromChannelGroupMessage<T : MessageContent> : GroupMessage<T>, SignedMessage, WithSenderChatMessage {
    val channel: ChannelChat
    override val senderChat: ChannelChat
        get() = channel
}
interface AnonymousGroupMessage<T : MessageContent> : GroupMessage<T>, SignedMessage, WithSenderChatMessage {
    override val senderChat: GroupChat
        get() = chat
}
interface CommonGroupMessage<T : MessageContent> : GroupMessage<T>
