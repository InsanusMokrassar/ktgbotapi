package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.boldHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.boldMarkdown

class BoldTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.boldMarkdown()
    override val asHtmlSource: String = sourceString.boldHTML()
}
