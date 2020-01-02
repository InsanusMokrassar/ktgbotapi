package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.italicHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.italicMarkdown

class ItalicTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.italicMarkdown()
    override val asHtmlSource: String
        get() = rawSource.italicHTML()
}
