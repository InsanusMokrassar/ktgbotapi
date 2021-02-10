package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.types.message.content.abstracts.PossiblySentViaBotCommonMessage

interface ChannelContentMessage<T: MessageContent> : PossiblySentViaBotCommonMessage<T>, SignedMessage, WithSenderChatMessage {
    override val chat: ChannelChat
    override val senderChat: ChannelChat
        get() = chat
}
@Deprecated("Renamed due to ambiguity of naming", ReplaceWith("ChannelContentMessage", "dev.inmo.tgbotapi.types.message.abstracts.ChannelContentMessage"))
typealias ChannelMessage<T> = ChannelContentMessage<T>
