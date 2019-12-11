package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.codeHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.codeMarkdown

class CodeTextSource(
    sourceString: String
) : TextSource {
    override val asMarkdownSource: String = sourceString.codeMarkdown()
    override val asHtmlSource: String = sourceString.codeHTML()
}
