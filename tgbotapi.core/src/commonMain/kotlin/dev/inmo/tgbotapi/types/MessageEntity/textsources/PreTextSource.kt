package dev.inmo.tgbotapi.types.MessageEntity.textsources

/**
 * @see pre
 */
@Deprecated("Replaced", ReplaceWith("PreTextSource", "dev.inmo.tgbotapi.types.message.textsources.PreTextSource"))
typealias PreTextSource = dev.inmo.tgbotapi.types.message.textsources.PreTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("pre", "dev.inmo.tgbotapi.types.message.textsources.pre"))
inline fun pre(code: String, language: String? = null) = dev.inmo.tgbotapi.types.message.textsources.pre(code, language)

