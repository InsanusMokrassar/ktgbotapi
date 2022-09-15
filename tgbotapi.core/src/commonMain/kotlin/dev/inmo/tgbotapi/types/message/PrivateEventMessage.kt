package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PrivateChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage

data class PrivateEventMessage<T : PrivateEvent>(
    override val messageId: MessageId,
    override val chat: PrivateChat,
    override val chatEvent: T,
    override val date: DateTime
) : ChatEventMessage<T>
