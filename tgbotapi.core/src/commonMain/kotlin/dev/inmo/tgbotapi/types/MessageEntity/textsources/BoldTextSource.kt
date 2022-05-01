package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see bold
 */
@Deprecated("Replaced", ReplaceWith("BoldTextSource", "dev.inmo.tgbotapi.types.message.textsources.BoldTextSource"))
typealias BoldTextSource = dev.inmo.tgbotapi.types.message.textsources.BoldTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("bold", "dev.inmo.tgbotapi.types.message.textsources.bold"))
inline fun bold(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.bold(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("bold", "dev.inmo.tgbotapi.types.message.textsources.bold"))
inline fun bold(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.bold(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("bold", "dev.inmo.tgbotapi.types.message.textsources.bold"))
inline fun bold(text: String) = dev.inmo.tgbotapi.types.message.textsources.bold(text)
