package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

interface MessageEntity {
    val offset: Int
    val length: Int
    val sourceString: String
    
    val asMarkdownSource: String
    val asHtmlSource: String
}
