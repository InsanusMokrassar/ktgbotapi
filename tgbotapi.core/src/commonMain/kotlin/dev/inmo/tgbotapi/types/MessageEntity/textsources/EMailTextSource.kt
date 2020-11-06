package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

/**
 * @see email
 */
data class EMailTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.emailMarkdown() }
    override val asMarkdownV2Source: String by lazy { emailMarkdownV2(source) }
    override val asHtmlSource: String by lazy { emailHTML(source) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun email(parts: List<TextSource>) = EMailTextSource(parts.makeString(), parts)
@Suppress("NOTHING_TO_INLINE")
inline fun email(vararg parts: TextSource) = email(parts.toList())
@Suppress("NOTHING_TO_INLINE")
inline fun email(emailAddress: String) = email(regular(emailAddress))
