package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.DirectInvocationOfTextSourceConstructor
import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*

/**
 * @see pre
 */
data class PreTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val language: String? = null
) : TextSource {
    override val markdown: String by lazy { source.preMarkdown(language) }
    override val markdownV2: String by lazy { source.preMarkdownV2(language) }
    override val html: String by lazy { source.preHTML(language) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun pre(code: String, language: String? = null) = PreTextSource(code, language)

