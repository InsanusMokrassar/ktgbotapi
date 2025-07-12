package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see blockquoteTextSource
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

fun blockquoteTextSource(parts: TextSourcesList) = BlockquoteTextSource(parts.makeString(), parts)
fun blockquoteTextSource(vararg parts: TextSource) = blockquoteTextSource(parts.toList())
fun blockquoteTextSource(text: String) = blockquoteTextSource(regularTextSource(text))
