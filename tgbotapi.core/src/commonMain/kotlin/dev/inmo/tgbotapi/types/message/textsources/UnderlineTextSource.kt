package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see underline
 */
@Serializable
data class UnderlineTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.underlineMarkdown() }
    override val markdownV2: String by lazy { underlineMarkdownV2() }
    override val html: String by lazy { underlineHTML() }
}

inline fun underline(parts: TextSourcesList) = UnderlineTextSource(parts.makeString(), parts)
inline fun underline(vararg parts: TextSource) = underline(parts.toList())
inline fun underline(text: String) = underline(regular(text))
