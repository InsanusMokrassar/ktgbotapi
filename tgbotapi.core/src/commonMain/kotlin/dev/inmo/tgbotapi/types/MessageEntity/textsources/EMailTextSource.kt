package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see email
 */
@Deprecated("Replaced", ReplaceWith("EMailTextSource", "dev.inmo.tgbotapi.types.message.textsources.EMailTextSource"))
typealias EMailTextSource = dev.inmo.tgbotapi.types.message.textsources.EMailTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("email", "dev.inmo.tgbotapi.types.message.textsources.email"))
inline fun email(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.email(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("email", "dev.inmo.tgbotapi.types.message.textsources.email"))
inline fun email(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.email(*parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("email", "dev.inmo.tgbotapi.types.message.textsources.email"))
inline fun email(emailAddress: String) = dev.inmo.tgbotapi.types.message.textsources.email(emailAddress)
