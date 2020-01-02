package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.underlineHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.underlineMarkdown

class UnderlineTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.underlineMarkdown()
    override val asHtmlSource: String
        get() = rawSource.underlineHTML()
}