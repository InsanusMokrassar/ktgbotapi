package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.MultilevelTextSource
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.utils.*

class UnderlineTextSource(
    override val source: String,
    textParts: List<TextPart>
) : MultilevelTextSource {
    override val textParts: List<TextPart> by lazy { source.fullListOfSubSource(textParts) }
    override val asMarkdownSource: String by lazy { source.underlineMarkdown() }
    override val asMarkdownV2Source: String by lazy { underlineMarkdownV2() }
    override val asHtmlSource: String by lazy { underlineHTML() }
}