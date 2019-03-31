package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.italicHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.italicMarkdown

data class ItalicTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.italicMarkdown()
    override val asHtmlSource: String = sourceString.italicHTML()
}
