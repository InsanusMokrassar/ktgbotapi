package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.mentionMarkdown
import dev.inmo.tgbotapi.utils.internal.mentionMarkdownV2

private val String.withoutCommercialAt
    get() = if (startsWith("@")) {
        substring(1)
    } else {
        this
    }

/**
 * @see mention
 */
data class MentionTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val markdown: String by lazy { source.mentionMarkdown() }
    override val markdownV2: String by lazy { mentionMarkdownV2() }
    override val html: String by lazy { mentionHTML() }

    init {
        if (!source.startsWith("@")) {
            error("Mention source must starts with @, but actual value is \"$source\"")
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun mention(parts: List<TextSource>) = (regular("@") + parts).let { MentionTextSource(it.makeString(), it) }
@Suppress("NOTHING_TO_INLINE")
inline fun mention(vararg parts: TextSource) = mention(parts.toList())

/**
 * Without leading "@"
 */
@Suppress("NOTHING_TO_INLINE")
inline fun mention(whoToMention: String) = mention(regular(whoToMention))

