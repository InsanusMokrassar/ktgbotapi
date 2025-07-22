package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see boldTextSource
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

fun boldTextSource(parts: TextSourcesList) = BoldTextSource(parts.makeString(), parts)
fun boldTextSource(vararg parts: TextSource) = boldTextSource(parts.toList())
fun boldTextSource(text: String) = boldTextSource(regularTextSource(text))
