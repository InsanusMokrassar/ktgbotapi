package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.bot.GetMe
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.message.textsources.MentionTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextMentionTextSource
import kotlinx.coroutines.flow.*

suspend fun BehaviourContext.waitMentionMessages(
    chatId: ChatId? = null,
    username: Username? = null,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<MessageContent>> {
    return waitContentMessage<MessageContent>(initRequest, includeMediaGroups, errorFactory).filter {
        val content = (it.content as? TextedInput) ?: return@filter false

        content.textSources.any {
            (it is MentionTextSource && (username == null || it.username == username))
            || (it is TextMentionTextSource && (chatId == null || it.user.id == chatId))
        }
    }
}

suspend fun BehaviourContext.waitMentionMessages(
    user: User,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<MessageContent>> = waitMentionMessages(user.id, user.username, initRequest, includeMediaGroups, errorFactory)

/**
 * This expectation will get bot info and then
 * Currently it is expected that the bot username will not be changed in runtime
 */
suspend fun BehaviourContext.waitBotMentionMessages(
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<MessageContent>> = waitMentionMessages(execute(GetMe), initRequest, includeMediaGroups, errorFactory)
