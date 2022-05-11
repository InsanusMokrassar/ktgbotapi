package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see code
 */
@Serializable
data class CodeTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String
) : TextSource {
    override val markdown: String by lazy { source.codeMarkdown() }
    override val markdownV2: String by lazy { source.codeMarkdownV2() }
    override val html: String by lazy { source.codeHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun code(code: String) = CodeTextSource(code)
