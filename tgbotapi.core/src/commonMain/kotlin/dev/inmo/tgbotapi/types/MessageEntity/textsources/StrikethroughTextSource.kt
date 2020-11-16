package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.strikethroughMarkdown
import dev.inmo.tgbotapi.utils.internal.strikethroughMarkdownV2

/**
 * @see strikethrough
 */
data class StrikethroughTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val html: String by lazy { strikethroughHTML() }
    override val markdownV2: String by lazy { strikethroughMarkdownV2() }
    override val markdown: String by lazy { source.strikethroughMarkdown() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(parts: List<TextSource>) = StrikethroughTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(vararg parts: TextSource) = strikethrough(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(text: String) = strikethrough(regular(text))