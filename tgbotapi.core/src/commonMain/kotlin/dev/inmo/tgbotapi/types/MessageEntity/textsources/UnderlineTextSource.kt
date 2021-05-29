package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see underline
 */
@Serializable
data class UnderlineTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val markdown: String by lazy { source.underlineMarkdown() }
    override val markdownV2: String by lazy { underlineMarkdownV2() }
    override val html: String by lazy { underlineHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun underline(parts: List<TextSource>) = UnderlineTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun underline(vararg parts: TextSource) = underline(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun underline(text: String) = underline(regular(text))
