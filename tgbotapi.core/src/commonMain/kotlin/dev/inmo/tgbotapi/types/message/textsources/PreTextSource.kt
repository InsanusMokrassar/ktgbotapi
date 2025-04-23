package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see preTextSource
 */
@Serializable
data class PreTextSource
@RiskFeature(DirectInvocationOfTextSourceConstructor)
constructor(
    override val source: String,
    val language: String? = null,
) : TextSource {
    override val markdown: String by lazy { source.preMarkdown(language) }
    override val markdownV2: String by lazy { source.preMarkdownV2(language) }
    override val html: String by lazy { source.preHTML(language) }
}

inline fun preTextSource(
    code: String,
    language: String? = null,
) = PreTextSource(code, language)
