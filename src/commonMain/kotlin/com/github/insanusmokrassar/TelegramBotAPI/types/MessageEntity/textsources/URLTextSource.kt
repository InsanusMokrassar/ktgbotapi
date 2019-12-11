package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.linkMarkdown

class URLTextSource(
    sourceString: String
) : TextSource{
    override val asMarkdownSource: String = sourceString.linkMarkdown(sourceString)
    override val asHtmlSource: String = sourceString.linkHTML(sourceString)
}
