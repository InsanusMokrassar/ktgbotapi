package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.types.Identifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.textsources.mention

/**
 * @see mention
 */
@Deprecated("Replaced", ReplaceWith("TextMentionTextSource", "dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource"))
typealias TextMentionTextSource = dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(parts: TextSourcesList, user: User) = mention(parts, user)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("User.mention", "dev.inmo.tgbotapi.types.message.textsources.User.mention"))
inline fun User.mention(parts: TextSourcesList) = mention(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(parts: TextSourcesList, userId: UserId) = mention(parts, userId)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("UserId.mention", "dev.inmo.tgbotapi.types.message.textsources.UserId.mention"))
inline fun UserId.mention(parts: TextSourcesList) = mention(parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(parts: TextSourcesList, id: Identifier) = mention(parts, id)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("Identifier.mention", "dev.inmo.tgbotapi.types.message.textsources.Identifier.mention"))
inline fun Identifier.mention(parts: TextSourcesList) = mention(parts)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(user: User, vararg parts: TextSource) = mention(user, *parts)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(text: String, user: User) = mention(text, user)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("User.mention", "dev.inmo.tgbotapi.types.message.textsources.User.mention"))
inline fun User.mention(text: String) = mention(text)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(text: String, userId: UserId) = mention(text, userId)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("UserId.mention", "dev.inmo.tgbotapi.types.message.textsources.UserId.mention"))
inline fun UserId.mention(text: String) = mention(text)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("mention", "dev.inmo.tgbotapi.types.message.textsources.mention"))
inline fun mention(text: String, id: Identifier) = mention(text, id)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("Identifier.mention", "dev.inmo.tgbotapi.types.message.textsources.Identifier.mention"))
inline fun Identifier.mention(text: String) = mention(text)
