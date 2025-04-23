package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewSupergroupChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent
import dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage
import korlibs.time.DateTime

data class CommonSupergroupEventMessage<T : SupergroupEvent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: PreviewSupergroupChat,
    override val chatEvent: T,
    override val date: DateTime,
) : SupergroupEventMessage<T>
