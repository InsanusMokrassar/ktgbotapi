package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.preHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.preMarkdown

class PreTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.preMarkdown()
    override val asHtmlSource: String = sourceString.preHTML()
}
