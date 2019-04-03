package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class MentionMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.mentionMarkdown()
    override val asHtmlSource: String = sourceString.mentionHTML()
}
