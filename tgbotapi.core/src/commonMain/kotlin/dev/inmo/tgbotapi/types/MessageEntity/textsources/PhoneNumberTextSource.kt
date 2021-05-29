package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see phone
 */
@Serializable
data class PhoneNumberTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val markdown: String by lazy { source.phoneMarkdown() }
    override val markdownV2: String by lazy { phoneMarkdownV2() }
    override val html: String by lazy { phoneHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun phone(parts: List<TextSource>) = PhoneNumberTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun phone(vararg parts: TextSource) = phone(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun phone(number: String) = phone(regular(number))

