package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.Chat
import com.soywiz.klock.DateTime

interface Message {
    val messageId: MessageIdentifier
    val chat: Chat
    val date: DateTime
}