package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see mentionTextSource
 */
@Serializable
data class TextMentionTextSource
    @RiskFeature(DirectInvocationOfTextSourceConstructor)
    constructor(
        override val source: String,
        val user: User,
        override val subsources: TextSourcesList,
    ) : MultilevelTextSource {
        override val markdown: String by lazy { source.textMentionMarkdown(user.id) }
        override val markdownV2: String by lazy { textMentionMarkdownV2(user.id) }
        override val html: String by lazy { textMentionHTML(user.id) }
    }

inline fun mentionTextSource(
    parts: TextSourcesList,
    user: User,
) = TextMentionTextSource(parts.makeString(), user, parts)

inline fun User.mentionTextSource(parts: TextSourcesList) = mentionTextSource(parts, this)

inline fun mentionTextSource(
    parts: TextSourcesList,
    userId: UserId,
) = mentionTextSource(parts, CommonUser(userId, ""))

inline fun UserId.mentionTextSource(parts: TextSourcesList) = mentionTextSource(parts, this)

inline fun mentionTextSource(
    parts: TextSourcesList,
    id: RawChatId,
) = mentionTextSource(parts, UserId(id))

inline fun RawChatId.mentionTextSource(parts: TextSourcesList) = mentionTextSource(parts, this)

inline fun mentionTextSource(
    user: User,
    vararg parts: TextSource,
) = mentionTextSource(
    textSourcesOrElseTextSource(parts.toList()) {
        RegularTextSource("${user.lastName} ${user.firstName}")
    },
    user,
)

inline fun mentionTextSource(
    text: String,
    user: User,
) = mentionTextSource(user, regularTextSource(text))

inline fun User.mentionTextSource(text: String) = mentionTextSource(this, regularTextSource(text))

inline fun mentionTextSource(
    text: String,
    userId: UserId,
) = mentionTextSource(text, CommonUser(userId, ""))

inline fun UserId.mentionTextSource(text: String) = mentionTextSource(text, this)

inline fun mentionTextSource(
    text: String,
    id: RawChatId,
) = mentionTextSource(text, UserId(id))

inline fun RawChatId.mentionTextSource(text: String) = mentionTextSource(text, this)
