package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see italic
 */
@Serializable
data class ItalicTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.italicMarkdown() }
    override val markdownV2: String by lazy { italicMarkdownV2() }
    override val html: String by lazy { italicHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun italic(parts: TextSourcesList) = ItalicTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun italic(vararg parts: TextSource) = italic(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun italic(text: String) = italic(regular(text))

