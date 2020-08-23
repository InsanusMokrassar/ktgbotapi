package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.*

class CodeTextSource(
    override val source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.codeMarkdown() }
    override val asMarkdownV2Source: String by lazy { source.codeMarkdownV2() }
    override val asHtmlSource: String by lazy { source.codeHTML() }
}
