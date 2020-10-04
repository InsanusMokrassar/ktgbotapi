package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.*

class RegularTextSource(
    override val source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.regularMarkdown() }
    override val asMarkdownV2Source: String by lazy { source.regularMarkdownV2() }
    override val asHtmlSource: String by lazy { source.regularHtml() }
}
