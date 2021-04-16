package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see hashtag
 */
@Serializable
data class HashTagTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val markdown: String by lazy { source.hashTagMarkdown() }
    override val markdownV2: String by lazy { hashTagMarkdownV2() }
    override val html: String by lazy { hashTagHTML() }

    init {
        if (!source.startsWith("#")) {
            error("HashTag source must starts with #, but actual value is \"$source\"")
        }
    }
}

@Suppress("NOTHING_TO_INLINE", "EXPERIMENTAL_API_USAGE")
inline fun hashtag(parts: List<TextSource>) = (regular("#") + parts).let { HashTagTextSource(it.makeString(), it) }
@Suppress("NOTHING_TO_INLINE")
inline fun hashtag(vararg parts: TextSource) = hashtag(parts.toList())
/**
 * Without sharp (#)
 */
@Suppress("NOTHING_TO_INLINE")
inline fun hashtag(hashtag: String) = hashtag(regular(hashtag))
