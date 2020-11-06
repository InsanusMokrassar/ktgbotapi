package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

class UnderlineTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.underlineMarkdown() }
    override val asMarkdownV2Source: String by lazy { underlineMarkdownV2() }
    override val asHtmlSource: String by lazy { underlineHTML() }
}