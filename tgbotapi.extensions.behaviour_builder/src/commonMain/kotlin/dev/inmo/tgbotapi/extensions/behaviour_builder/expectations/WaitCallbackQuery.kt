@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.CallbackQuery.*
import kotlinx.coroutines.flow.toList

typealias CallbackQueryMapper<T> = T.() -> T?

private suspend fun <O> BehaviourContext.waitCallbackQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    mapper: suspend CallbackQuery.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    it.asCallbackQueryUpdate() ?.data ?.mapper()
}.toList().toList()


private suspend inline fun <reified T : CallbackQuery> BehaviourContext.waitEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: CallbackQueryMapper<T>? = null
) : List<T> = waitCallbackQueries<T>(
    count,
    initRequest,
    errorFactory
) {
    if (this is T) {
        if (filter == null) {
            this
        } else {
            filter(this)
        }
    } else {
        null
    }
}


suspend fun BehaviourContext.waitDataCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<DataCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGameShortNameCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<GameShortNameCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<InlineMessageIdCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdDataCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<InlineMessageIdDataCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdGameShortNameCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<InlineMessageIdGameShortNameCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<MessageCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageDataCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<MessageDataCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageGameShortNameCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<MessageGameShortNameCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitUnknownCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<UnknownCallbackQueryType>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
