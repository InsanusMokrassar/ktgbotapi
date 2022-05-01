package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see mention
 */
@Serializable
data class TextMentionTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val user: User,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String by lazy { source.textMentionMarkdown(user.id) }
    override val markdownV2: String by lazy { textMentionMarkdownV2(user.id) }
    override val html: String by lazy { textMentionHTML(user.id) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun mention(parts: TextSourcesList, user: User) = TextMentionTextSource(parts.makeString(), user, parts)
@Suppress("NOTHING_TO_INLINE")
inline fun User.mention(parts: TextSourcesList) = mention(parts, this)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(parts: TextSourcesList, userId: UserId) = mention(parts, CommonUser(userId, ""))
@Suppress("NOTHING_TO_INLINE")
inline fun UserId.mention(parts: TextSourcesList) = mention(parts, this)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(parts: TextSourcesList, id: Identifier) = mention(parts, UserId(id))
@Suppress("NOTHING_TO_INLINE")
inline fun Identifier.mention(parts: TextSourcesList) = mention(parts, this)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(user: User, vararg parts: TextSource) = mention(
    textSourcesOrElseTextSource(parts.toList()) {
        RegularTextSource("${user.lastName} ${user.firstName}")
    },
    user
)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(text: String, user: User) = mention(user, regular(text))
@Suppress("NOTHING_TO_INLINE")
inline fun User.mention(text: String) = mention(this, regular(text))
@Suppress("NOTHING_TO_INLINE")
inline fun mention(text: String, userId: UserId) = mention(text, CommonUser(userId, ""))
@Suppress("NOTHING_TO_INLINE")
inline fun UserId.mention(text: String) = mention(text, this)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(text: String, id: Identifier) = mention(text, UserId(id))
@Suppress("NOTHING_TO_INLINE")
inline fun Identifier.mention(text: String) = mention(text, this)
