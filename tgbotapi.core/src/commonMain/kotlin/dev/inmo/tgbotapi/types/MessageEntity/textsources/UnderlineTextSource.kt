package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

/**
 * @see underline
 */
data class UnderlineTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.underlineMarkdown() }
    override val asMarkdownV2Source: String by lazy { underlineMarkdownV2() }
    override val asHtmlSource: String by lazy { underlineHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun underline(parts: List<TextSource>) = UnderlineTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun underline(vararg parts: TextSource) = underline(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun underline(text: String) = underline(regular(text))