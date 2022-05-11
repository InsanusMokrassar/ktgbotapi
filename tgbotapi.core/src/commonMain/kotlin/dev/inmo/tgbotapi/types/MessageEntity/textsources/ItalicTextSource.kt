package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see italic
 */
@Deprecated("Replaced", ReplaceWith("ItalicTextSource", "dev.inmo.tgbotapi.types.message.textsources.ItalicTextSource"))
typealias ItalicTextSource = dev.inmo.tgbotapi.types.message.textsources.ItalicTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("italic", "dev.inmo.tgbotapi.types.message.textsources.italic"))
inline fun italic(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.italic(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("italic", "dev.inmo.tgbotapi.types.message.textsources.italic"))
inline fun italic(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.italic(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("italic", "dev.inmo.tgbotapi.types.message.textsources.italic"))
inline fun italic(text: String) = dev.inmo.tgbotapi.types.message.textsources.italic(text)

