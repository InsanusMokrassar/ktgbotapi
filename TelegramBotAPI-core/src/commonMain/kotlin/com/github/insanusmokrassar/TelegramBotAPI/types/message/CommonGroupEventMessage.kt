package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.GroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.GroupEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.GroupEventMessage
import com.soywiz.klock.DateTime

@Deprecated("Renamed", ReplaceWith("CommonGroupEventMessage"))
typealias GroupEventMessage = CommonGroupEventMessage

data class CommonGroupEventMessage(
    override val messageId: MessageIdentifier,
    override val user: User,
    override val chat: GroupChat,
    override val chatEvent: GroupEvent,
    override val date: DateTime
) : GroupEventMessage
