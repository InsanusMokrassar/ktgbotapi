package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toHtml
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toMarkdown

data class RegularTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.toMarkdown()
    override val asHtmlSource: String = sourceString.toHtml()
}
