package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChannelChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.ChannelEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ChatEventMessage
import org.joda.time.DateTime

data class ChannelEventMessage(
    override val messageId: MessageIdentifier,
    override val chat: ChannelChat,
    override val chatEvent: ChannelEvent,
    override val date: DateTime
) : ChatEventMessage
