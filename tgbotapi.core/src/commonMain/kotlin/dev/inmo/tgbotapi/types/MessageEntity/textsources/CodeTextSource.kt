package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.DirectInvocationOfTextSourceConstructor
import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.codeMarkdown
import dev.inmo.tgbotapi.utils.internal.codeMarkdownV2

/**
 * @see code
 */
data class CodeTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String
) : TextSource {
    override val markdown: String by lazy { source.codeMarkdown() }
    override val markdownV2: String by lazy { source.codeMarkdownV2() }
    override val html: String by lazy { source.codeHTML() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun code(code: String) = CodeTextSource(code)
