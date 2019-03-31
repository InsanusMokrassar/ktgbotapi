package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.boldHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.boldMarkdown

data class BoldTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.boldMarkdown()
    override val asHtmlSource: String = sourceString.boldHTML()
}
