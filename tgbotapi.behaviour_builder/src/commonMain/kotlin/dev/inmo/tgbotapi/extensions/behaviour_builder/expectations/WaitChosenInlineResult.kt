package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.*
import dev.inmo.tgbotapi.types.polls.*
import kotlinx.coroutines.flow.toList

typealias ChosenInlineResultMapper<T> = suspend T.() -> T?

private suspend fun <O> BehaviourContext.waitChosenInlineResultsUpdates(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<ChosenInlineResult>? = null,
    mapper: suspend ChosenInlineResult.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asChosenInlineResultUpdate() ?.data
    if (data != null && (filter == null || filter(data))) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


private suspend inline fun <reified T : ChosenInlineResult> BehaviourContext.waitChosenInlineResults(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<T>? = null,
    noinline mapper: ChosenInlineResultMapper<T>? = null
) : List<T> = this@waitChosenInlineResults.waitChosenInlineResultsUpdates<T>(
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

suspend fun BehaviourContext.waitChosenInlineResult(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChosenInlineResult>? = null,
    mapper: ChosenInlineResultMapper<ChosenInlineResult>? = null
) = waitChosenInlineResults(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitLocationChosenInlineResult(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<LocationChosenInlineResult>? = null,
    mapper: PollMapper<LocationChosenInlineResult>? = null
) = waitChosenInlineResults(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitBaseChosenInlineResult(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<BaseChosenInlineResult>? = null,
    mapper: PollMapper<BaseChosenInlineResult>? = null
) = waitChosenInlineResults(count, initRequest, errorFactory, filter, mapper)
