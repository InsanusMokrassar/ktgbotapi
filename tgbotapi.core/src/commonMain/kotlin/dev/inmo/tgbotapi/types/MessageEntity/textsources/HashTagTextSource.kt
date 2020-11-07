package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.hashTagMarkdown
import dev.inmo.tgbotapi.utils.internal.hashTagMarkdownV2

/**
 * @see hashtag
 */
data class HashTagTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.hashTagMarkdown() }
    override val asMarkdownV2Source: String by lazy { hashTagMarkdownV2() }
    override val asHtmlSource: String by lazy { hashTagHTML() }

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
