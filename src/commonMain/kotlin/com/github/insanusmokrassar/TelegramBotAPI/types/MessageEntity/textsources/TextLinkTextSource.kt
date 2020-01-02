package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkMarkdown

class TextLinkTextSource(
    override val rawSource: String,
    url: String
) : TextSource {
    override val asMarkdownSource: String = rawSource.linkMarkdown(url)
    override val asHtmlSource: String = rawSource.linkHTML(url)
}
