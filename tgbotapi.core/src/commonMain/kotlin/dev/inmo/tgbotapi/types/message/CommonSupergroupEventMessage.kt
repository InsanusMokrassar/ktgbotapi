package dev.inmo.tgbotapi.types.message

import com.soywiz.klock.DateTime
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.chat.abstracts.SupergroupChat
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.SupergroupEvent
import dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage

@Deprecated("Renamed", ReplaceWith("CommonSupergroupEventMessage"))
typealias SupergroupEventMessage = CommonSupergroupEventMessage

data class CommonSupergroupEventMessage(
    override val messageId: MessageIdentifier,
    override val user: User,
    override val chat: SupergroupChat,
    override val chatEvent: SupergroupEvent,
    override val date: DateTime
) : SupergroupEventMessage
