package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see phone
 */
@Deprecated("Replaced", ReplaceWith("PhoneNumberTextSource", "dev.inmo.tgbotapi.types.message.textsources.PhoneNumberTextSource"))
typealias PhoneNumberTextSource = dev.inmo.tgbotapi.types.message.textsources.PhoneNumberTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("phone", "dev.inmo.tgbotapi.types.message.textsources.phone"))
inline fun phone(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.phone(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("phone", "dev.inmo.tgbotapi.types.message.textsources.phone"))
inline fun phone(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.phone(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("phone", "dev.inmo.tgbotapi.types.message.textsources.phone"))
inline fun phone(number: String) = dev.inmo.tgbotapi.types.message.textsources.phone(number)

