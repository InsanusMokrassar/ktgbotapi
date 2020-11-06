package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

/**
 * @see link
 */
data class TextLinkTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val url: String
) : TextSource {
    override val asMarkdownSource: String by lazy { source.linkMarkdown(url) }
    override val asMarkdownV2Source: String by lazy { source.linkMarkdownV2(url) }
    override val asHtmlSource: String by lazy { source.linkHTML(url) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun link(text: String, url: String) = TextLinkTextSource(text, url)
