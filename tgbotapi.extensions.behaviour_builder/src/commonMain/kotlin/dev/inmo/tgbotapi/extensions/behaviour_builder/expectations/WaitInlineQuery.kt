package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asInlineQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.InlineQueries.query.*
import kotlinx.coroutines.flow.toList

typealias InlineQueryMapper<T> = suspend T.() -> T?

private suspend fun <O> BehaviourContext.waitInlineQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<InlineQuery>? = null,
    mapper: suspend InlineQuery.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asInlineQueryUpdate() ?.data
    if (data != null && (filter == null || filter(data))) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


private suspend inline fun <reified T : InlineQuery> BehaviourContext.waitInlines(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<InlineQuery>? = null,
    noinline mapper: InlineQueryMapper<T>? = null
) : List<T> = waitInlineQueries<T>(
    count,
    initRequest,
    errorFactory,
    filter
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

suspend fun BehaviourContext.waitAnyInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<InlineQuery>? = null,
    mapper: InlineQueryMapper<InlineQuery>? = null
) = waitInlines(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitBaseInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<BaseInlineQuery>? = null,
    mapper: InlineQueryMapper<BaseInlineQuery>? = null
) = waitInlines(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitLocationInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<LocationInlineQuery>? = null,
    mapper: InlineQueryMapper<LocationInlineQuery>? = null
) = waitInlines(count, initRequest, errorFactory, filter, mapper)
