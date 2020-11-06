package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

/**
 * @see link
 */
data class URLTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.linkMarkdown(source) }
    override val asMarkdownV2Source: String by lazy { source.linkMarkdownV2(source) }
    override val asHtmlSource: String by lazy { source.linkHTML(source) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun link(url: String) = URLTextSource(url)
