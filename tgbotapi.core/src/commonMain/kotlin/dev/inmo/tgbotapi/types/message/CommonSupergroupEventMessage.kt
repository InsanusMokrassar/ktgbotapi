package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewSupergroupChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.SupergroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent
import dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage

data class CommonSupergroupEventMessage<T : SupergroupEvent>(
    override val messageId: MessageId,
    override val from: User,
    override val chat: PreviewSupergroupChat,
    override val chatEvent: T,
    override val date: DateTime
) : SupergroupEventMessage<T>
