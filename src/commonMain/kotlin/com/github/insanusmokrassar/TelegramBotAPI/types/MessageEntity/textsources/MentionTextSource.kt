package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class MentionTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.mentionMarkdown()
    override val asHtmlSource: String = sourceString.mentionHTML()
}
