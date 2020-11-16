package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.regularMarkdown
import dev.inmo.tgbotapi.utils.internal.regularMarkdownV2

/**
 * @see regular
 */
data class RegularTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String
) : TextSource {
    override val markdown: String by lazy { source.regularMarkdown() }
    override val markdownV2: String by lazy { source.regularMarkdownV2() }
    override val html: String by lazy { source.regularHtml() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun regular(text: String) = RegularTextSource(text)
