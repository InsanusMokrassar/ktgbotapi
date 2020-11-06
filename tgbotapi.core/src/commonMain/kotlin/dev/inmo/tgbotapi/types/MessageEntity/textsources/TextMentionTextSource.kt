package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.utils.*

/**
 * @see mention
 */
data class TextMentionTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val user: User,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.textMentionMarkdown(user.id) }
    override val asMarkdownV2Source: String by lazy { textMentionMarkdownV2(user.id) }
    override val asHtmlSource: String by lazy { textMentionHTML(user.id) }
}

@Suppress("NOTHING_TO_INLINE")
inline fun mention(parts: List<TextSource>, user: User) = TextMentionTextSource(parts.makeString(), user, parts)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(user: User, vararg parts: TextSource) = mention(parts.toList(), user)
@Suppress("NOTHING_TO_INLINE")
inline fun mention(text: String, user: User) = mention(user, regular(text))
