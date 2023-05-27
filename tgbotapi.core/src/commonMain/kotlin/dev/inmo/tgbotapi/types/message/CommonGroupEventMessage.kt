package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.GroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent
import dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage

data class CommonGroupEventMessage<T : GroupEvent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: GroupChat,
    override val chatEvent: T,
    override val date: DateTime
) : GroupEventMessage<T>
