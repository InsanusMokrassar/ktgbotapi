package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.hashTagMarkdown

data class HashTagMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.hashTagMarkdown()
    override val asHtmlSource: String = sourceString.hashTagHTML()
}
