package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see strikethroughTextSource
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

fun strikethroughTextSource(parts: TextSourcesList) = StrikethroughTextSource(parts.makeString(), parts)
fun strikethroughTextSource(vararg parts: TextSource) = strikethroughTextSource(parts.toList())
fun strikethroughTextSource(text: String) = strikethroughTextSource(regularTextSource(text))
