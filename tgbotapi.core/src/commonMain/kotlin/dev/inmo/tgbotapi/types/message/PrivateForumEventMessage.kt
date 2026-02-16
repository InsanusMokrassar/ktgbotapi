package dev.inmo.tgbotapi.types.message

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewPrivateChat
import dev.inmo.tgbotapi.types.chat.PreviewPrivateForumChat
import dev.inmo.tgbotapi.types.chat.PrivateChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateForumEvent
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage

data class PrivateForumEventMessage<T : PrivateForumEvent>(
    override val messageId: MessageId,
    override val chat: PreviewPrivateForumChat,
    override val chatEvent: T,
    override val date: DateTime
) : ChatEventMessage<T>
