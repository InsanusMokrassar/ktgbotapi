@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.queries.callback.*
import kotlinx.coroutines.flow.toList

typealias CallbackQueryMapper<T> = suspend T.() -> T?

private suspend fun <O> BehaviourContext.waitCallbackQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CallbackQuery>? = null,
    mapper: suspend CallbackQuery.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asCallbackQueryUpdate() ?.data ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


private suspend inline fun <reified T : CallbackQuery> BehaviourContext.waitCallbacks(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<T>? = null,
    noinline mapper: CallbackQueryMapper<T>? = null
) : List<T> = waitCallbackQueries<T>(
    count,
    initRequest,
    errorFactory,
    filter ?.let {
        {
            (it as? T) ?.let { filter(it) } == true
        }
    }
) {
    if (this is T) {
        if (mapper == null) {
            this
        } else {
            mapper(this)
        }
    } else {
        null
    }
}


suspend fun BehaviourContext.waitDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<DataCallbackQuery>? = null,
    mapper: CallbackQueryMapper<DataCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<GameShortNameCallbackQuery>? = null,
    mapper: CallbackQueryMapper<GameShortNameCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitInlineMessageIdCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<InlineMessageIdCallbackQuery>? = null,
    mapper: CallbackQueryMapper<InlineMessageIdCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitInlineMessageIdDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<InlineMessageIdDataCallbackQuery>? = null,
    mapper: CallbackQueryMapper<InlineMessageIdDataCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitInlineMessageIdGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<InlineMessageIdGameShortNameCallbackQuery>? = null,
    mapper: CallbackQueryMapper<InlineMessageIdGameShortNameCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitMessageCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<MessageCallbackQuery>? = null,
    mapper: CallbackQueryMapper<MessageCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitMessageDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<MessageDataCallbackQuery>? = null,
    mapper: CallbackQueryMapper<MessageDataCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitMessageGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<MessageGameShortNameCallbackQuery>? = null,
    mapper: CallbackQueryMapper<MessageGameShortNameCallbackQuery>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitUnknownCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<UnknownCallbackQueryType>? = null,
    mapper: CallbackQueryMapper<UnknownCallbackQueryType>? = null
) = waitCallbacks(count, initRequest, errorFactory, filter, mapper)
