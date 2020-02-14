package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.ChannelChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.ChannelEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ChatEventMessage
import com.soywiz.klock.DateTime

data class ChannelEventMessage(
    override val messageId: MessageIdentifier,
    override val chat: ChannelChat,
    override val chatEvent: ChannelEvent,
    override val date: DateTime
) : ChatEventMessage
