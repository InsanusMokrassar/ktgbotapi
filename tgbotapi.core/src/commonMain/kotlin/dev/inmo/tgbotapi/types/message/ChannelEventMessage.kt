package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage

data class ChannelEventMessage<T : ChannelEvent>(
    override val messageId: MessageId,
    override val chat: ChannelChat,
    override val chatEvent: T,
    override val date: DateTime
) : ChatEventMessage<T>
