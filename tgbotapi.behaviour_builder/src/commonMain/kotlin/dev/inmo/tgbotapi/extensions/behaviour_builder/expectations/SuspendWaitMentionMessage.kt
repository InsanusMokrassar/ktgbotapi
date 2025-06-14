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

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [username] has been mentioned
 *
 * @see filterMentions
 * @see filterTextMentions
 */
suspend fun BehaviourContext.waitContentMessageWithMentions (
    username: Username,
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedContent>().filterMentionsMessages(username)

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [userId] has been mentioned with text
 *
 * @see filterTextMentions
 * @see filterMentions
 * @see dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource
 */
suspend fun BehaviourContext.waitContentMessageWithTextMentions (
    userId: UserId,
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitTextedContentMessage(initRequest, errorFactory).filterTextMentionsMessages(userId)

/**
 * Creates cold [Flow] with the messages with [TextedContent] where [user] has been mentioned as text or mentioned
 * with text
 *
 * @see filterMentions
 * @see filterTextMentions
 */
suspend fun BehaviourContext.waitContentMessageWithMentions (
    user: User,
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitTextedContentMessage(initRequest, errorFactory).filterMentionsMessages(user)
