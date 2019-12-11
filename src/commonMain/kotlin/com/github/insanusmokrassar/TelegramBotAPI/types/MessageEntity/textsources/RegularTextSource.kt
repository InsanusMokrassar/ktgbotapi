package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toHtml
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toMarkdown

class RegularTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.toMarkdown()
    override val asHtmlSource: String = sourceString.toHtml()
}
