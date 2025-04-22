package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewGroupChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent
import dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage
import korlibs.time.DateTime

data class CommonGroupEventMessage<T : GroupEvent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: PreviewGroupChat,
    override val chatEvent: T,
    override val date: DateTime,
) : GroupEventMessage<T>
