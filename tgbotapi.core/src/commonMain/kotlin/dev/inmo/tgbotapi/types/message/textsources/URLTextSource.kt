package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see link
 */
@Serializable
data class URLTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String
) : TextSource {
    override val markdown: String by lazy { source.linkMarkdown(source) }
    override val markdownV2: String by lazy { source.linkMarkdownV2(source) }
    override val html: String by lazy { source.linkHTML(source) }
}

inline fun link(url: String) = URLTextSource(url)
