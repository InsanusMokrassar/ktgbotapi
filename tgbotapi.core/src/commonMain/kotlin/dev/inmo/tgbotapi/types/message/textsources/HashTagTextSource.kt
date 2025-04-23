package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see hashtagTextSource
 */
@Serializable
data class HashTagTextSource
@RiskFeature(DirectInvocationOfTextSourceConstructor)
constructor(
    override val source: String,
    override val subsources: TextSourcesList,
) : MultilevelTextSource {
    val username: Username? by lazy {
        val potentialUsername = source.dropWhile { it != '@' }
        if (potentialUsername.isEmpty()) return@lazy null

        Username(potentialUsername)
    }

    override val markdown: String by lazy { source.hashTagMarkdown() }
    override val markdownV2: String by lazy { hashTagMarkdownV2() }
    override val html: String by lazy { hashTagHTML() }

    init {
        if (!source.startsWith("#")) {
            error("HashTag source must starts with #, but actual value is \"$source\"")
        }
    }
}

@Suppress("EXPERIMENTAL_API_USAGE")
inline fun hashtagTextSource(parts: TextSourcesList) = (regularTextSource("#") + parts).let { HashTagTextSource(it.makeString(), it) }

inline fun hashtagTextSource(vararg parts: TextSource) = hashtagTextSource(parts.toList())

/**
 * Without sharp (#)
 */
inline fun hashtagTextSource(hashtag: String) = hashtagTextSource(regularTextSource(hashtag))
