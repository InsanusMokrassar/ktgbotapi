package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.cashTagMarkdown
import dev.inmo.tgbotapi.utils.internal.cashTagMarkdownV2

/**
 * @see cashTag
 */
data class CashTagTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.cashTagMarkdown() }
    override val asMarkdownV2Source: String by lazy { cashTagMarkdownV2() }
    override val asHtmlSource: String by lazy { cashTagHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun cashTag(parts: List<TextSource>) = CashTagTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun cashTag(vararg parts: TextSource) = cashTag(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun cashTag(tag: String) = cashTag(regular(tag))
