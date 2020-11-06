package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.MessageEntity.toTextParts
import dev.inmo.tgbotapi.utils.*

class BoldTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.boldMarkdown() }
    override val asMarkdownV2Source: String by lazy { boldMarkdownV2() }
    override val asHtmlSource: String by lazy { boldHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun bold(text: String) = BoldTextSource(text, emptyList())
@Suppress("NOTHING_TO_INLINE")
inline fun bold(parts: List<TextSource>) = BoldTextSource(parts.makeString(), parts)
