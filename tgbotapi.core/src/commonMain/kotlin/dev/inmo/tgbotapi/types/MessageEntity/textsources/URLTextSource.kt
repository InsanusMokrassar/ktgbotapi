package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.linkMarkdown
import dev.inmo.tgbotapi.utils.internal.linkMarkdownV2

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
