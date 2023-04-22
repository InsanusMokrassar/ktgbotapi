package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see customEmoji
 */
@Serializable
data class CustomEmojiTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val customEmojiId: CustomEmojiId,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.customEmojiMarkdown() }
    override val markdownV2: String by lazy { source.customEmojiMarkdownV2(customEmojiId) }
    override val html: String by lazy { source.customEmojiHTML(customEmojiId) }
}

@Suppress("NOTHING_TO_INLINE", "EXPERIMENTAL_API_USAGE")
inline fun customEmoji(emojiId: CustomEmojiId, parts: TextSourcesList) = CustomEmojiTextSource(parts.makeString(), emojiId, parts)
@Suppress("NOTHING_TO_INLINE")
inline fun customEmoji(emojiId: CustomEmojiId, vararg parts: TextSource) = customEmoji(emojiId, parts.toList())
/**
 * Without sharp (#)
 */
@Suppress("NOTHING_TO_INLINE")
inline fun customEmoji(emojiId: CustomEmojiId, text: String) = customEmoji(emojiId, regular(text))
