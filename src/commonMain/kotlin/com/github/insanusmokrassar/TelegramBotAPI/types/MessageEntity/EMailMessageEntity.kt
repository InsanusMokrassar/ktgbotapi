package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.emailHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.emailMarkdown

class EMailMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.emailMarkdown()
    override val asHtmlSource: String = sourceString.emailHTML()
}
