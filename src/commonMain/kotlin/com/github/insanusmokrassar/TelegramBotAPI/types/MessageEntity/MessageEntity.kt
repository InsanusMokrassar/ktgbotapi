package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource

interface MessageEntity : TextSource {
    val offset: Int
    val length: Int
    @Deprecated("Due to opportunity to get the same string from rawSource const", ReplaceWith("rawSource"))
    val sourceString: String
        get() = rawSource
}
