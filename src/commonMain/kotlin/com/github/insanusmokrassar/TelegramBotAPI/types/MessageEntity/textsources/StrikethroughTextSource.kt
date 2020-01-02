package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.strikethroughHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.strikethroughMarkdown

class StrikethroughTextSource(
    sourceString: String
) : TextSource {
    override val asHtmlSource: String = sourceString.strikethroughHTML()
    override val asMarkdownSource: String = sourceString.strikethroughMarkdown()
}