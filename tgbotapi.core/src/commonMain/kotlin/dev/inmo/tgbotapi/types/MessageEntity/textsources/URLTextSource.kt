package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.internal.link

/**
 * @see link
 */
@Deprecated("Replaced", ReplaceWith("URLTextSource", "dev.inmo.tgbotapi.types.message.textsources.URLTextSource"))
typealias URLTextSource = dev.inmo.tgbotapi.types.message.textsources.URLTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("link", "dev.inmo.tgbotapi.types.message.textsources.link"))
inline fun link(url: String) = dev.inmo.tgbotapi.types.message.textsources.link(url)
