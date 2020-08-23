package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

interface ContentMessage<T: MessageContent>: Message {
    val content: T
}
