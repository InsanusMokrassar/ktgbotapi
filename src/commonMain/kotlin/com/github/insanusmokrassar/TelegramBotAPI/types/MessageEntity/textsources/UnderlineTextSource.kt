package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.underlineHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.underlineMarkdown

class UnderlineTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.underlineMarkdown()
    override val asHtmlSource: String = sourceString.underlineHTML()
}