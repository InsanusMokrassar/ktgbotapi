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
 * @see mentionTextSource
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

inline fun mentionTextSource(parts: TextSourcesList) = (regularTextSource("@") + parts).let { MentionTextSource(it.makeString(), it) }
inline fun mentionTextSource(vararg parts: TextSource) = mentionTextSource(parts.toList())

/**
 * Without leading "@"
 */
inline fun mentionTextSource(whoToMention: String) = mentionTextSource(regularTextSource(whoToMention))

inline fun mentionTextSource(whoToMention: Username) = mentionTextSource(whoToMention.full.dropWhile { it == '@' })

