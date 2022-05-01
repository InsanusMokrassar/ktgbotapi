package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see strikethrough
 */
@Deprecated("Replaced", ReplaceWith("StrikethroughTextSource", "dev.inmo.tgbotapi.types.message.textsources.StrikethroughTextSource"))
typealias StrikethroughTextSource = dev.inmo.tgbotapi.types.message.textsources.StrikethroughTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("strikethrough", "dev.inmo.tgbotapi.types.message.textsources.strikethrough"))
inline fun strikethrough(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.strikethrough(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("strikethrough", "dev.inmo.tgbotapi.types.message.textsources.strikethrough"))
inline fun strikethrough(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.strikethrough(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("strikethrough", "dev.inmo.tgbotapi.types.message.textsources.strikethrough"))
inline fun strikethrough(text: String) = dev.inmo.tgbotapi.types.message.textsources.strikethrough(text)
