package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import korlibs.time.DateTime

data class PrivateEventMessage<T : PrivateEvent>(
    override val messageId: MessageId,
    override val chat: PreviewPrivateChat,
    override val chatEvent: T,
    override val date: DateTime,
) : ChatEventMessage<T>
