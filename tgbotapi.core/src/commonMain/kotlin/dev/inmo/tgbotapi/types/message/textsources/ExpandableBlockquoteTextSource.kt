package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see expandableBlockquoteTextSource
 */
@Serializable
data class ExpandableBlockquoteTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.expandableBlockquoteMarkdown() }
    override val markdownV2: String by lazy { expandableBlockquoteMarkdownV2() }
    override val html: String by lazy { expandableBlockquoteHTML() }
}

fun expandableBlockquoteTextSource(parts: TextSourcesList) = ExpandableBlockquoteTextSource(parts.makeString(), parts)
fun expandableBlockquoteTextSource(vararg parts: TextSource) = expandableBlockquoteTextSource(parts.toList())
fun expandableBlockquoteTextSource(text: String) = expandableBlockquoteTextSource(regularTextSource(text))
