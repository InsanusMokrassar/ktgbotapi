package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.DirectInvocationOfTextSourceConstructor
import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*

/**
 * @see link
 */
data class TextLinkTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val url: String
) : TextSource {
    override val markdown: String by lazy { source.linkMarkdown(url) }
    override val markdownV2: String by lazy { source.linkMarkdownV2(url) }
    override val html: String by lazy { source.linkHTML(url) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun link(text: String, url: String) = TextLinkTextSource(text, url)
