package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.boldHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.boldMarkdown

class BoldTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.boldMarkdown()
    override val asHtmlSource: String
        get() = rawSource.boldHTML()
}
