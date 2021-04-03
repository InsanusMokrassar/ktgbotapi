package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.asInlineQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery
import kotlinx.coroutines.flow.toList

typealias InlineQueryMapper<T> = suspend T.() -> T?

private suspend fun <O> BehaviourContext.waitInlineQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    mapper: suspend InlineQuery.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    it.asInlineQueryUpdate() ?.data ?.mapper().let(::listOfNotNull)
}.toList().toList()


private suspend inline fun <reified T : InlineQuery> BehaviourContext.waitInlines(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: InlineQueryMapper<T>? = null
) : List<T> = waitInlineQueries<T>(
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

suspend fun BehaviourContext.waitAnyInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: InlineQueryMapper<InlineQuery>? = null
) = waitInlines(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitBaseInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: InlineQueryMapper<BaseInlineQuery>? = null
) = waitInlines(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitLocationInlineQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: InlineQueryMapper<LocationInlineQuery>? = null
) = waitInlines(count, initRequest, errorFactory, filter)
