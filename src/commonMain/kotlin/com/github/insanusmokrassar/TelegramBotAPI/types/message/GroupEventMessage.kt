package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.GroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.GroupEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ChatEventMessage
import com.soywiz.klock.DateTime

data class GroupEventMessage(
    override val messageId: MessageIdentifier,
    override val chat: GroupChat,
    override val chatEvent: GroupEvent,
    override val date: DateTime
) : ChatEventMessage
