package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see cashTag
 */
@Deprecated("Replaced", ReplaceWith("CashTagTextSource", "dev.inmo.tgbotapi.types.message.textsources.CashTagTextSource"))
typealias CashTagTextSource = dev.inmo.tgbotapi.types.message.textsources.CashTagTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("cashTag", "dev.inmo.tgbotapi.types.message.textsources.cashTag"))
inline fun cashTag(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.cashTag(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("cashTag", "dev.inmo.tgbotapi.types.message.textsources.cashTag"))
inline fun cashTag(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.cashTag(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("cashTag", "dev.inmo.tgbotapi.types.message.textsources.cashTag"))
inline fun cashTag(tag: String) = dev.inmo.tgbotapi.types.message.textsources.cashTag(tag)
