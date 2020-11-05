package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.chat.abstracts.GroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent
import dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage

@Deprecated("Renamed", ReplaceWith("CommonGroupEventMessage"))
typealias GroupEventMessage = CommonGroupEventMessage

data class CommonGroupEventMessage(
    override val messageId: MessageIdentifier,
    override val user: User,
    override val chat: GroupChat,
    override val chatEvent: GroupEvent,
    override val date: DateTime
) : GroupEventMessage
