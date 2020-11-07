package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.phoneMarkdown
import dev.inmo.tgbotapi.utils.internal.phoneMarkdownV2

/**
 * @see phone
 */
data class PhoneNumberTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.phoneMarkdown() }
    override val asMarkdownV2Source: String by lazy { phoneMarkdownV2() }
    override val asHtmlSource: String by lazy { phoneHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun phone(parts: List<TextSource>) = PhoneNumberTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun phone(vararg parts: TextSource) = phone(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun phone(number: String) = phone(regular(number))

