package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.internal.code

/**
 * @see code
 */
@Deprecated("Replaced", ReplaceWith("CodeTextSource", "dev.inmo.tgbotapi.types.message.textsources.CodeTextSource"))
typealias CodeTextSource = dev.inmo.tgbotapi.types.message.textsources.CodeTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("code", "dev.inmo.tgbotapi.types.message.textsources.code"))
inline fun code(code: String) = dev.inmo.tgbotapi.types.message.textsources.code(code)
