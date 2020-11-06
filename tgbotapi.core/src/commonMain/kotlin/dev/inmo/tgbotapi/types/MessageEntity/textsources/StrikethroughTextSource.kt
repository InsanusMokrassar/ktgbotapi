package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

/**
 * @see strikethrough
 */
data class StrikethroughTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asHtmlSource: String by lazy { strikethroughHTML() }
    override val asMarkdownV2Source: String by lazy { strikethroughMarkdownV2() }
    override val asMarkdownSource: String by lazy { source.strikethroughMarkdown() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(parts: List<TextSource>) = StrikethroughTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(vararg parts: TextSource) = strikethrough(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(text: String) = strikethrough(regular(text))