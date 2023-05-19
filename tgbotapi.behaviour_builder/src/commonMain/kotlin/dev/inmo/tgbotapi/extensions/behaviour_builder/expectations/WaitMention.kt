package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.whenMentionTextSource
import dev.inmo.tgbotapi.extensions.utils.whenTextMentionTextSource
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.message.content.TextedContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

/**
 * Check, that [TextedContent.textSources] contains:
 *
 * * Any [dev.inmo.tgbotapi.types.message.textsources.MentionTextSource] with [dev.inmo.tgbotapi.types.message.textsources.MentionTextSource.username]
 * equal to [username]
 * * Any [dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource] with [dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource.user]
 * with the same [username]
 */
fun TextedContent.isWithMention(username: Username) = textSources.any {
    it.whenMentionTextSource {
        it.username == username
    } ?: it.whenTextMentionTextSource {
        it.user.username == username
    } ?: false
}

/**
 * Check, that [TextedContent.textSources] contains:
 *
 * * Any [dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource] with [dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource.user]
 * with the same [userId]
 */
fun TextedContent.isWithTextMention(userId: UserId) = textSources.any {
    it.whenTextMentionTextSource {
        it.user.id == userId
    } ?: false
}

/**
 * Uses [isWithMention] with [user] [Username] (is presented) or [isWithTextMention] with [user] [UserId] to determine
 * user mentioning in [this] [CommonMessage]
 */
fun TextedContent.isWithMention(user: User): Boolean = user.username ?.let { username -> isWithMention(username) } == true || isWithTextMention(user.id)

/**
 * Uses [isWithMention] passing [username] as argument to take only messages with [username] mentions or text mentions
 */
fun Flow<TextedContent>.filterMentions(username: Username) = filter {
    it.isWithMention(username)
}

/**
 * Uses [isWithTextMention] passing [userId] as argument to take only messages with [userId] text mentions
 */
fun Flow<TextedContent>.filterTextMentions(userId: UserId) = filter {
    it.isWithTextMention(userId)
}

/**
 * Uses [isWithMention] passing [user] as argument to take only messages with [user] mentions or text mentions
 */
fun Flow<TextedContent>.filterMentions(user: User) = filter {
    it.isWithMention(user)
}

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [username] has been mentioned
 *
 * @see filterMentions
 * @see filterTextMentions
 */
suspend fun BehaviourContext.waitContentWithMentions (
    username: Username,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<TextedContent>().filterMentions(username)

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [userId] has been mentioned with text
 *
 * @see filterTextMentions
 * @see filterMentions
 * @see dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource
 */
suspend fun BehaviourContext.waitContentWithTextMentions (
    userId: UserId,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitTextedContent(initRequest, errorFactory).filterTextMentions(userId)

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [user] has been mentioned as text or mentioned
 * with text
 *
 * @see filterMentions
 * @see filterTextMentions
 */
suspend fun BehaviourContext.waitContentWithMentions (
    user: User,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitTextedContent(initRequest, errorFactory).filterMentions(user)
