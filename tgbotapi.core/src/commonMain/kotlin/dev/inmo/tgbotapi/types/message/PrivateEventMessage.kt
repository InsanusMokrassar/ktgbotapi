package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import dev.inmo.tgbotapi.types.chat.abstracts.PrivateChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage

data class PrivateEventMessage<T : PrivateEvent>(
    override val messageId: MessageIdentifier,
    override val chat: PrivateChat,
    override val chatEvent: T,
    override val date: DateTime
) : ChatEventMessage<T>
