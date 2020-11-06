package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

class StrikethroughTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asHtmlSource: String by lazy { strikethroughHTML() }
    override val asMarkdownV2Source: String by lazy { strikethroughMarkdownV2() }
    override val asMarkdownSource: String by lazy { source.strikethroughMarkdown() }
}