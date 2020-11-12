package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.boldMarkdown
import dev.inmo.tgbotapi.utils.internal.boldMarkdownV2

/**
 * @see bold
 */
data class BoldTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.boldMarkdown() }
    override val asMarkdownV2Source: String by lazy { boldMarkdownV2() }
    override val asHtmlSource: String by lazy { boldHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun bold(parts: List<TextSource>) = BoldTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun bold(vararg parts: TextSource) = bold(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun bold(text: String) = bold(regular(text))
