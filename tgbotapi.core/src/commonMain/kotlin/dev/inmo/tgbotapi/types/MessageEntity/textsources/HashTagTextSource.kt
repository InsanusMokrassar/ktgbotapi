package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see hashtag
 */
@Deprecated("Replaced", ReplaceWith("HashTagTextSource", "dev.inmo.tgbotapi.types.message.textsources.HashTagTextSource"))
typealias HashTagTextSource = dev.inmo.tgbotapi.types.message.textsources.HashTagTextSource

@Suppress("NOTHING_TO_INLINE", "EXPERIMENTAL_API_USAGE")
@Deprecated("Replaced", ReplaceWith("hashtag", "dev.inmo.tgbotapi.types.message.textsources.hashtag"))
inline fun hashtag(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.hashtag(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("hashtag", "dev.inmo.tgbotapi.types.message.textsources.hashtag"))
inline fun hashtag(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.hashtag(*parts)
/**
 * Without sharp (#)
 */
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("hashtag", "dev.inmo.tgbotapi.types.message.textsources.hashtag"))
inline fun hashtag(hashtag: String) = dev.inmo.tgbotapi.types.message.textsources.hashtag(hashtag)
