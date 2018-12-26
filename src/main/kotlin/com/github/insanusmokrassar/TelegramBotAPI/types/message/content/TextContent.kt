package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

data class TextContent(
    val text: String,
    val entities: List<MessageEntity> = emptyList()
) : MessageContent
