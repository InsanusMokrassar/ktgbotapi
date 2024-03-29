package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see bold
 */
@Serializable
data class BoldTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.boldMarkdown() }
    override val markdownV2: String by lazy { boldMarkdownV2() }
    override val html: String by lazy { boldHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun bold(parts: TextSourcesList) = BoldTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun bold(vararg parts: TextSource) = bold(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun bold(text: String) = bold(regular(text))
