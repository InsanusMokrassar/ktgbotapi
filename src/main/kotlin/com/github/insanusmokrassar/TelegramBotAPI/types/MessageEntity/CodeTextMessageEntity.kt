package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.codeHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.codeMarkdown

data class CodeTextMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.codeMarkdown()
    override val asHtmlSource: String = sourceString.codeHTML()
}
