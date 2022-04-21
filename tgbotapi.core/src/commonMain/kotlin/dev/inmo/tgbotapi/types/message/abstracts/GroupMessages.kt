package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.chat.GroupChat
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

interface GroupContentMessage<T : MessageContent> : PublicContentMessage<T> {
    override val chat: GroupChat
}


interface FromChannelGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, SignedMessage, WithSenderChatMessage {
    val channel: ChannelChat
    override val senderChat: ChannelChat
        get() = channel
}

interface ConnectedFromChannelGroupContentMessage<T: MessageContent> : FromChannelGroupContentMessage<T>
interface UnconnectedFromChannelGroupContentMessage<T: MessageContent> : FromChannelGroupContentMessage<T>

interface AnonymousGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, SignedMessage, WithSenderChatMessage {
    override val senderChat: GroupChat
        get() = chat
}

interface CommonGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, FromUserMessage
