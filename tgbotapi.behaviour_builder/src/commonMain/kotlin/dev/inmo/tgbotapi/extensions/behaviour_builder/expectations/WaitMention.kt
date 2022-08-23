package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.bot.GetMe
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.coroutines.flow.*

suspend fun BehaviourContext.waitMentionContents(
    chatId: ChatId? = null,
    username: Username? = null,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MessageContent> = waitMentionMessages(chatId, username, initRequest, includeMediaGroups, errorFactory).map { it.content }

suspend fun BehaviourContext.waitMentionContents(
    user: User,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MessageContent> = waitMentionContents(user.id, user.username, initRequest, includeMediaGroups, errorFactory)

/**
 * This expectation will get bot info and then
 * Currently it is expected that the bot username will not be changed in runtime
 */
suspend fun BehaviourContext.waitBotMentions(
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MessageContent> = waitMentionContents(execute(GetMe), initRequest, includeMediaGroups, errorFactory)
