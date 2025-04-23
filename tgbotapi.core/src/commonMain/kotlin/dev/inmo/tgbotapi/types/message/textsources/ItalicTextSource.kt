package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see italicTextSource
 */
@Serializable
data class ItalicTextSource
@RiskFeature(DirectInvocationOfTextSourceConstructor)
constructor(
    override val source: String,
    override val subsources: TextSourcesList,
) : MultilevelTextSource {
    override val markdown: String by lazy { source.italicMarkdown() }
    override val markdownV2: String by lazy { italicMarkdownV2() }
    override val html: String by lazy { italicHTML() }
}

inline fun italicTextSource(parts: TextSourcesList) = ItalicTextSource(parts.makeString(), parts)

inline fun italicTextSource(vararg parts: TextSource) = italicTextSource(parts.toList())

inline fun italicTextSource(text: String) = italicTextSource(regularTextSource(text))
