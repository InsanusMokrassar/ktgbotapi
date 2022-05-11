package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see regular
 */
@Deprecated("Replaced", ReplaceWith("RegularTextSource", "dev.inmo.tgbotapi.types.message.textsources.RegularTextSource"))
typealias RegularTextSource = dev.inmo.tgbotapi.types.message.textsources.RegularTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("regular", "dev.inmo.tgbotapi.types.message.textsources.regular"))
inline fun regular(text: String) = dev.inmo.tgbotapi.types.message.textsources.regular(text)
