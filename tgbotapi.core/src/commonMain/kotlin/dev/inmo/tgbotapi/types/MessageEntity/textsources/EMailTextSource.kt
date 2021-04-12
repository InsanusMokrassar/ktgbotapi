package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see email
 */
@Serializable
data class EMailTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val markdown: String by lazy { source.emailMarkdown() }
    override val markdownV2: String by lazy { emailMarkdownV2(source) }
    override val html: String by lazy { emailHTML(source) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun email(parts: List<TextSource>) = EMailTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun email(vararg parts: TextSource) = email(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun email(emailAddress: String) = email(regular(emailAddress))
