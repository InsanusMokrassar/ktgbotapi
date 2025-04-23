package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewChannelChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import korlibs.time.DateTime

data class ChannelEventMessage<T : ChannelEvent>(
    override val messageId: MessageId,
    override val chat: PreviewChannelChat,
    override val chatEvent: T,
    override val date: DateTime,
) : ChatEventMessage<T>
