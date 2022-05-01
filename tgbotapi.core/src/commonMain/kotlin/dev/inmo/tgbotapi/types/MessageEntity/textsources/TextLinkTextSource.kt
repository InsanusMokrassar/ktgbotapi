package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.internal.link

/**
 * @see link
 */
@Deprecated("Replaced", ReplaceWith("TextLinkTextSource", "dev.inmo.tgbotapi.types.message.textsources.TextLinkTextSource"))
typealias TextLinkTextSource = dev.inmo.tgbotapi.types.message.textsources.TextLinkTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("link", "dev.inmo.tgbotapi.types.message.textsources.link"))
inline fun link(text: String, url: String) = dev.inmo.tgbotapi.types.message.textsources.link(text, url)
