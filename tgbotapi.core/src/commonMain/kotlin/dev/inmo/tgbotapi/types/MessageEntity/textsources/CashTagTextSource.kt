package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*

/**
 * @see cashTag
 */
data class CashTagTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val markdown: String by lazy { source.cashTagMarkdown() }
    override val markdownV2: String by lazy { cashTagMarkdownV2() }
    override val html: String by lazy { cashTagHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun cashTag(parts: List<TextSource>) = CashTagTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun cashTag(vararg parts: TextSource) = cashTag(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun cashTag(tag: String) = cashTag(regular(tag))
