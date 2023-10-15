package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface GroupContentMessage<T : MessageContent> : PublicContentMessage<T> {
    override val chat: PreviewGroupChat
}

sealed interface ForumContentMessage<T : MessageContent> : GroupContentMessage<T>, PossiblyTopicMessage {
    override val chat: PreviewForumChat
    override val threadId: MessageThreadId
}


sealed interface FromChannelGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, SignedMessage, WithSenderChatMessage {
    val channel: PreviewChannelChat
    override val senderChat: PreviewChannelChat
        get() = channel
}

interface ConnectedFromChannelGroupContentMessage<T: MessageContent> : FromChannelGroupContentMessage<T>
interface UnconnectedFromChannelGroupContentMessage<T: MessageContent> : FromChannelGroupContentMessage<T>

interface AnonymousGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, SignedMessage, WithSenderChatMessage {
    override val senderChat: PreviewGroupChat
        get() = chat
}

interface CommonGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, FromUserMessage

interface FromChannelForumContentMessage<T: MessageContent> : FromChannelGroupContentMessage<T>, ForumContentMessage<T>

interface AnonymousForumContentMessage<T : MessageContent> : ForumContentMessage<T>, SignedMessage, WithSenderChatMessage {
    override val senderChat: PreviewGroupChat
        get() = chat
}

interface CommonForumContentMessage<T : MessageContent> : ForumContentMessage<T>, FromUserMessage
