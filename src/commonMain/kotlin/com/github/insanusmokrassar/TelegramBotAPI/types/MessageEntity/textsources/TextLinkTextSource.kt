package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkMarkdown

class TextLinkTextSource(
    sourceString: String,
    url: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.linkMarkdown(url)
    override val asHtmlSource: String = sourceString.linkHTML(url)
}
