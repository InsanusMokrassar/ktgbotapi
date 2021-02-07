package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.chat.abstracts.GroupChat
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

interface GroupContentMessage<T : MessageContent> : PublicContentMessage<T> {
    override val chat: GroupChat
}
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("GroupContentMessage", "dev.inmo.tgbotapi.types.message.abstracts.GroupContentMessage"))
typealias GroupMessage<T> = GroupContentMessage<T>


interface FromChannelGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, SignedMessage, WithSenderChatMessage {
    val channel: ChannelChat
    override val senderChat: ChannelChat
        get() = channel
}
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("FromChannelGroupContentMessage", "dev.inmo.tgbotapi.types.message.abstracts.FromChannelGroupContentMessage"))
typealias FromChannelGroupMessage<T> = FromChannelGroupContentMessage<T>

interface AnonymousGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, SignedMessage, WithSenderChatMessage {
    override val senderChat: GroupChat
        get() = chat
}
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("AnonymousGroupContentMessage", "dev.inmo.tgbotapi.types.message.abstracts.AnonymousGroupContentMessage"))
typealias AnonymousGroupMessage<T> = AnonymousGroupContentMessage<T>

interface CommonGroupContentMessage<T : MessageContent> : GroupContentMessage<T>, FromUserMessage
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("CommonGroupContentMessage", "dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage"))
typealias CommonGroupMessage<T> = CommonGroupContentMessage<T>
