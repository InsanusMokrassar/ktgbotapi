package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.preMarkdown
import dev.inmo.tgbotapi.utils.internal.preMarkdownV2

/**
 * @see pre
 */
data class PreTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val language: String? = null
) : TextSource {
    override val asMarkdownSource: String by lazy { source.preMarkdown(language) }
    override val asMarkdownV2Source: String by lazy { source.preMarkdownV2(language) }
    override val asHtmlSource: String by lazy { source.preHTML(language) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun pre(code: String, language: String? = null) = PreTextSource(code, language)

