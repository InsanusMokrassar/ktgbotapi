package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.italicHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.italicMarkdown

class ItalicTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.italicMarkdown()
    override val asHtmlSource: String = sourceString.italicHTML()
}
