package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

private val String.withoutCommercialAt
    get() = if (startsWith("@")) {
        substring(1)
    } else {
        this
    }

/**
 * @see mention
 */
@Serializable
data class MentionTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.mentionMarkdown() }
    override val markdownV2: String by lazy { mentionMarkdownV2() }
    override val html: String by lazy { mentionHTML() }
    val username: Username = Username(source)

    init {
        if (!source.startsWith("@")) {
            error("Mention source must starts with @, but actual value is \"$source\"")
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun mention(parts: TextSourcesList) = (regular("@") + parts).let { MentionTextSource(it.makeString(), it) }
@Suppress("NOTHING_TO_INLINE")
inline fun mention(vararg parts: TextSource) = mention(parts.toList())

/**
 * Without leading "@"
 */
@Suppress("NOTHING_TO_INLINE")
inline fun mention(whoToMention: String) = mention(regular(whoToMention))

@Suppress("NOTHING_TO_INLINE")
inline fun mention(whoToMention: Username) = mention(whoToMention.username.dropWhile { it == '@' })

