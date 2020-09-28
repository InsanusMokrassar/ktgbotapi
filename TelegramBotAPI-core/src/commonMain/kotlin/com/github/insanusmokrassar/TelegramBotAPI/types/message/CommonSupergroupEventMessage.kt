package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.SupergroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.SupergroupEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.SupergroupEventMessage
import com.soywiz.klock.DateTime

@Deprecated("Renamed", ReplaceWith("CommonSupergroupEventMessage"))
typealias SupergroupEventMessage = CommonSupergroupEventMessage

data class CommonSupergroupEventMessage(
    override val messageId: MessageIdentifier,
    override val user: User,
    override val chat: SupergroupChat,
    override val chatEvent: SupergroupEvent,
    override val date: DateTime
) : SupergroupEventMessage
