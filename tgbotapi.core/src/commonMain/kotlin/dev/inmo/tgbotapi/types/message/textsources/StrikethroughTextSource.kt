package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see strikethrough
 */
@Serializable
data class StrikethroughTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val html: String by lazy { strikethroughHTML() }
    override val markdownV2: String by lazy { strikethroughMarkdownV2() }
    override val markdown: String by lazy { source.strikethroughMarkdown() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(parts: TextSourcesList) = StrikethroughTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(vararg parts: TextSource) = strikethrough(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun strikethrough(text: String) = strikethrough(regular(text))
