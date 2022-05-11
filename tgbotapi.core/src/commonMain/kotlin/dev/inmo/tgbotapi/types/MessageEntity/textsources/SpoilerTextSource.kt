package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see italic
 */
@Deprecated("Replaced", ReplaceWith("SpoilerTextSource", "dev.inmo.tgbotapi.types.message.textsources.SpoilerTextSource"))
typealias SpoilerTextSource = dev.inmo.tgbotapi.types.message.textsources.SpoilerTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("spoiler", "dev.inmo.tgbotapi.types.message.textsources.spoiler"))
inline fun spoiler(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.spoiler(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("spoiler", "dev.inmo.tgbotapi.types.message.textsources.spoiler"))
inline fun spoiler(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.spoiler(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("spoiler", "dev.inmo.tgbotapi.types.message.textsources.spoiler"))
inline fun spoiler(text: String) = dev.inmo.tgbotapi.types.message.textsources.spoiler(text)

