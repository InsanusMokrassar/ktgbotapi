package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.chat.ForumChat
import dev.inmo.tgbotapi.types.chat.GroupChat
import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface GroupContentMessage<T : MessageContent> : PublicContentMessage<T> {
    override val chat: GroupChat
}

sealed interface ForumContentMessage<T : MessageContent> : GroupContentMessage<T> {
    override val chat: ForumChat
    val threadId: MessageThreadId
}


sealed interface FromChannelGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, SignedMessage, WithSenderChatMessage {
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

interface FromChannelForumContentMessage<T: MessageContent> : FromChannelGroupContentMessage<T>, ForumContentMessage<T>

interface AnonymousForumContentMessage<T : MessageContent> : ForumContentMessage<T>, SignedMessage, WithSenderChatMessage {
    override val senderChat: GroupChat
        get() = chat
}

interface CommonForumContentMessage<T : MessageContent> : ForumContentMessage<T>, FromUserMessage
