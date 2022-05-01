package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.types.Username

/**
 * @see mention
 */
@Deprecated("Replaced", ReplaceWith("MentionTextSource", "dev.inmo.tgbotapi.types.message.textsources.MentionTextSource"))
typealias MentionTextSource = dev.inmo.tgbotapi.types.message.textsources.MentionTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(parts: TextSourcesList) = dev.inmo.tgbotapi.types.message.textsources.mention(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(vararg parts: TextSource) = dev.inmo.tgbotapi.types.message.textsources.mention(*parts)

/**
 * Without leading "@"
 */
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(whoToMention: String) = dev.inmo.tgbotapi.types.message.textsources.mention(whoToMention)

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(whoToMention: Username) = dev.inmo.tgbotapi.types.message.textsources.mention(whoToMention)

