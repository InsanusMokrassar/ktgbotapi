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
    it.asCallbackQueryUpdate() ?.data ?.mapper().let(::listOfNotNull)
}.toList().toList()


private suspend inline fun <reified T : CallbackQuery> BehaviourContext.waitCallbacks(
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
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<DataCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<GameShortNameCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<InlineMessageIdCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<InlineMessageIdDataCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<InlineMessageIdGameShortNameCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<MessageCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<MessageDataCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<MessageGameShortNameCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitUnknownCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CallbackQueryMapper<UnknownCallbackQueryType>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter)
