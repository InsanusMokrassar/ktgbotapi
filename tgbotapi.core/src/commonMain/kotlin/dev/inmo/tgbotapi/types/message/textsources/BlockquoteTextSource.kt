package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see blockquote
 */
@Serializable
data class BlockquoteTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.blockquoteMarkdown() }
    override val markdownV2: String by lazy { blockquoteMarkdownV2() }
    override val html: String by lazy { blockquoteHTML() }
}

inline fun blockquote(parts: TextSourcesList) = BlockquoteTextSource(parts.makeString(), parts)
inline fun blockquote(vararg parts: TextSource) = blockquote(parts.toList())
inline fun blockquote(text: String) = blockquote(regular(text))
