package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see phone
 */
@Serializable
data class PhoneNumberTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.phoneMarkdown() }
    override val markdownV2: String by lazy { phoneMarkdownV2() }
    override val html: String by lazy { phoneHTML() }
}

inline fun phone(parts: TextSourcesList) = PhoneNumberTextSource(parts.makeString(), parts)
inline fun phone(vararg parts: TextSource) = phone(parts.toList())
inline fun phone(number: String) = phone(regular(number))

