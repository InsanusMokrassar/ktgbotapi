package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource

interface MessageEntity : TextSource {
    val offset: Int
    val length: Int
    val sourceString: String
}
