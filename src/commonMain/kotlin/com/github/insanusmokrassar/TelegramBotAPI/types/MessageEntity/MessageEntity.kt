package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toMarkdown

interface MessageEntity {
    val offset: Int
    val length: Int
    val sourceString: String
    
    val asMarkdownSource: String
        get() = sourceString.toMarkdown()
}
