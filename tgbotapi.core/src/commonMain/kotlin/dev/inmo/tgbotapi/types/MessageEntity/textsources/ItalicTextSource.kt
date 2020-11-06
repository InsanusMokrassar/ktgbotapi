package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

class ItalicTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.italicMarkdown() }
    override val asMarkdownV2Source: String by lazy { italicMarkdownV2() }
    override val asHtmlSource: String by lazy { italicHTML() }
}
