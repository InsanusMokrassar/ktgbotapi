package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.mentionMarkdown

class MentionTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.mentionMarkdown()
    override val asHtmlSource: String
        get() = rawSource.mentionHTML()
}
