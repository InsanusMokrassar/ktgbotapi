package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see emailTextSource
 */
@Serializable
data class EMailTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.emailMarkdown() }
    override val markdownV2: String by lazy { emailMarkdownV2(source) }
    override val html: String by lazy { emailHTML(source) }
}

fun emailTextSource(parts: TextSourcesList) = EMailTextSource(parts.makeString(), parts)
fun emailTextSource(vararg parts: TextSource) = emailTextSource(parts.toList())
fun emailTextSource(emailAddress: String) = emailTextSource(regularTextSource(emailAddress))
