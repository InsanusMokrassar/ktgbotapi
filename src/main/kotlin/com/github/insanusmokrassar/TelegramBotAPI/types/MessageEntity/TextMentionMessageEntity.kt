package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class TextMentionMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String,
    val user: User
) : MessageEntity {
    override val asMarkdownSource: String = sourceString.mentionMarkdown(user.id)
    override val asHtmlSource: String = sourceString.mentionMarkdown(user.id)
}
