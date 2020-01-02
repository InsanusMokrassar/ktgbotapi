package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.codeHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.codeMarkdown

class CodeTextSource(
    override val rawSource: String
) : TextSource {
    override val asMarkdownSource: String
        get() = rawSource.codeMarkdown()
    override val asHtmlSource: String
        get() = rawSource.codeHTML()
}
