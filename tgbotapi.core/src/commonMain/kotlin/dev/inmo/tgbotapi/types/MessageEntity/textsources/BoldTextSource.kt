package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.MultilevelTextSource
import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.utils.*

class BoldTextSource(
    override val source: String,
    textParts: List<TextPart>
) : MultilevelTextSource {
    override val textParts: List<TextPart> by lazy { source.fullListOfSubSource(textParts) }
    override val asMarkdownSource: String by lazy { source.boldMarkdown() }
    override val asMarkdownV2Source: String by lazy { boldMarkdownV2() }
    override val asHtmlSource: String by lazy { boldHTML() }
}
