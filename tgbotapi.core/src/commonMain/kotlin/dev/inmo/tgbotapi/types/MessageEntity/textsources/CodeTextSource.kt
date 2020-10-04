package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.*

class CodeTextSource(
    override val source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.codeMarkdown() }
    override val asMarkdownV2Source: String by lazy { source.codeMarkdownV2() }
    override val asHtmlSource: String by lazy { source.codeHTML() }
}
