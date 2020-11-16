package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.internal.*
import dev.inmo.tgbotapi.utils.internal.textMentionMarkdown
import dev.inmo.tgbotapi.utils.internal.textMentionMarkdownV2

/**
 * @see mention
 */
data class TextMentionTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val user: User,
    override val subsources: List<TextSource>
) : MultilevelTextSource {
    override val markdown: String by lazy { source.textMentionMarkdown(user.id) }
    override val markdownV2: String by lazy { textMentionMarkdownV2(user.id) }
    override val html: String by lazy { textMentionHTML(user.id) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun mention(parts: List<TextSource>, user: User) = TextMentionTextSource(parts.makeString(), user, parts)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(user: User, vararg parts: TextSource) = mention(parts.toList(), user)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(text: String, user: User) = mention(user, regular(text))
