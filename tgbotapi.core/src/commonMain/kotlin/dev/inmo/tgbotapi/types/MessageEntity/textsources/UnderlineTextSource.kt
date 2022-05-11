package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.internal.underline

/**
 * @see underline
 */
@Deprecated("Replaced", ReplaceWith("UnderlineTextSource", "dev.inmo.tgbotapi.types.message.textsources.UnderlineTextSource"))
typealias UnderlineTextSource = dev.inmo.tgbotapi.types.message.textsources.UnderlineTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("underline", "dev.inmo.tgbotapi.types.message.textsources.underline"))
inline fun underline(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.underline(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("underline", "dev.inmo.tgbotapi.types.message.textsources.underline"))
inline fun underline(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.underline(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("underline", "dev.inmo.tgbotapi.types.message.textsources.underline"))
inline fun underline(text: String) = dev.inmo.tgbotapi.types.message.textsources.underline(text)
