package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.*

class URLTextSource(
    override val source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.linkMarkdown(source) }
    override val asMarkdownV2Source: String by lazy { source.linkMarkdownV2(source) }
    override val asHtmlSource: String by lazy { source.linkHTML(source) }
}
