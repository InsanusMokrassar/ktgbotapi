package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see italic
 */
@Serializable
data class SpoilerTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.spoilerMarkdown() }
    override val markdownV2: String by lazy { spoilerMarkdownV2() }
    override val html: String by lazy { spoilerHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun spoiler(parts: TextSourcesList) = SpoilerTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun spoiler(vararg parts: TextSource) = spoiler(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun spoiler(text: String) = spoiler(regular(text))

