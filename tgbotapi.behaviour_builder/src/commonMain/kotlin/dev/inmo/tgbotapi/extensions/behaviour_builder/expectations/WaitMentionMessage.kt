package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.whenMentionTextSource
import dev.inmo.tgbotapi.extensions.utils.whenTextMentionTextSource
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextedContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

fun CommonMessage<TextedContent>.isWithMention(username: Username) = content.isWithMention(username)

fun CommonMessage<TextedContent>.isWithTextMention(userId: UserId) = content.isWithTextMention(userId)

/**
 * Uses [isWithMention] with [user] [Username] (is presented) or [isWithTextMention] with [user] [UserId] to determine
 * user mentioning in [this] [CommonMessage]
 */
fun CommonMessage<TextedContent>.isWithMention(user: User): Boolean = content.isWithMention(user)

/**
 * Uses [isWithMention] passing [username] as argument to take only messages with [username] mentions or text mentions
 */
fun Flow<CommonMessage<TextedContent>>.filterMentionsMessages(username: Username) = filter {
    it.isWithMention(username)
}

/**
 * Uses [isWithTextMention] passing [userId] as argument to take only messages with [userId] text mentions
 */
fun Flow<CommonMessage<TextedContent>>.filterTextMentionsMessages(userId: UserId) = filter {
    it.isWithTextMention(userId)
}

/**
 * Uses [isWithMention] passing [user] as argument to take only messages with [user] mentions or text mentions
 */
fun Flow<CommonMessage<TextedContent>>.filterMentionsMessages(user: User) = filter {
    it.isWithMention(user)
}

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [username] has been mentioned
 *
 * @see filterMentions
 * @see filterTextMentions
 */
fun BehaviourContext.waitContentMessageWithMentions (
    username: Username,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedContent>().filterMentionsMessages(username)

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [userId] has been mentioned with text
 *
 * @see filterTextMentions
 * @see filterMentions
 * @see dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource
 */
fun BehaviourContext.waitContentMessageWithTextMentions (
    userId: UserId,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitTextedContentMessage(initRequest, errorFactory).filterTextMentionsMessages(userId)

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [user] has been mentioned as text or mentioned
 * with text
 *
 * @see filterMentions
 * @see filterTextMentions
 */
fun BehaviourContext.waitContentMessageWithMentions (
    user: User,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitTextedContentMessage(initRequest, errorFactory).filterMentionsMessages(user)
