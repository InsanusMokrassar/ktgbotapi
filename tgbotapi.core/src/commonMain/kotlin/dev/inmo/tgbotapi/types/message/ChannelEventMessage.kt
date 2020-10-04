package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import com.soywiz.klock.DateTime

data class ChannelEventMessage(
    override val messageId: MessageIdentifier,
    override val chat: ChannelChat,
    override val chatEvent: ChannelEvent,
    override val date: DateTime
) : ChatEventMessage
