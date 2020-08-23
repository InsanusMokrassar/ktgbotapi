package com.github.insanusmokrassar.TelegramBotAPI.types.message

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.SupergroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ChatEvents.abstracts.SupergroupEvent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ChatEventMessage
import com.soywiz.klock.DateTime

data class SupergroupEventMessage(
    override val messageId: MessageIdentifier,
    override val chat: SupergroupChat,
    override val chatEvent: SupergroupEvent,
    override val date: DateTime
) : ChatEventMessage
