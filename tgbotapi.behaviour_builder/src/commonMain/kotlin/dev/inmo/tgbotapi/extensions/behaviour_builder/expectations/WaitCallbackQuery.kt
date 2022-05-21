@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.queries.callback.*
import kotlinx.coroutines.flow.toList

typealias CallbackQueryMapper<T> = suspend T.() -> T?

private suspend inline fun <reified O> BehaviourContext.waitCallbackQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<O>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asCallbackQueryUpdate() ?.data ?: return@expectFlow emptyList()
    if (data is O && (filter == null || filter(data))) {
        listOf(data)
    } else {
        emptyList()
    }
}.toList().toList()


suspend fun BehaviourContext.waitDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<DataCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<GameShortNameCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<InlineMessageIdCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<InlineMessageIdDataCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInlineMessageIdGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<InlineMessageIdGameShortNameCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<MessageCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<MessageDataCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMessageGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<MessageGameShortNameCallbackQuery>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitUnknownCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<UnknownCallbackQueryType>? = null
) = waitCallbackQueries(count, initRequest, errorFactory, filter)
