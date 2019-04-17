package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import org.joda.time.DateTime

interface Message {
    val messageId: MessageIdentifier
    val chat: Chat
    val date: DateTime
}