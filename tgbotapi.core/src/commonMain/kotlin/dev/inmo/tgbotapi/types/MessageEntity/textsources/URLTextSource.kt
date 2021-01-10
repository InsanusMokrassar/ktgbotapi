package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.DirectInvocationOfTextSourceConstructor
import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*

/**
 * @see link
 */
data class URLTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String
) : TextSource {
    override val markdown: String by lazy { source.linkMarkdown(source) }
    override val markdownV2: String by lazy { source.linkMarkdownV2(source) }
    override val html: String by lazy { source.linkHTML(source) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun link(url: String) = URLTextSource(url)
