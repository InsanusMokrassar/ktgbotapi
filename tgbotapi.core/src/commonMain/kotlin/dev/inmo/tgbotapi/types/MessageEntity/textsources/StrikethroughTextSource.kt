package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.MultilevelTextSource
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.utils.*

class StrikethroughTextSource(
    override val source: String,
    textParts: List<TextPart>
) : MultilevelTextSource {
    override val textParts: List<TextPart> by lazy { source.fullListOfSubSource(textParts) }
    override val asHtmlSource: String by lazy { strikethroughHTML() }
    override val asMarkdownV2Source: String by lazy { strikethroughMarkdownV2() }
    override val asMarkdownSource: String by lazy { source.strikethroughMarkdown() }
}