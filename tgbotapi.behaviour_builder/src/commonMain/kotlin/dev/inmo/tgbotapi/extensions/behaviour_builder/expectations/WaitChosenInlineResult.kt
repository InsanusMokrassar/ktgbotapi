package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asChosenInlineResultUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.*
import kotlinx.coroutines.flow.toList

typealias ChosenInlineResultMapper<T> = suspend T.() -> T?

private suspend inline fun <reified O> BehaviourContext.waitChosenInlineResults(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<O>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asChosenInlineResultUpdate() ?.data as? O ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        listOf(data)
    } else {
        emptyList()
    }
}.toList().toList()

suspend fun BehaviourContext.waitChosenInlineResult(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChosenInlineResult>? = null
) = waitChosenInlineResults(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitLocationChosenInlineResult(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<LocationChosenInlineResult>? = null
) = waitChosenInlineResults(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitBaseChosenInlineResult(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<BaseChosenInlineResult>? = null
) = waitChosenInlineResults(count, initRequest, errorFactory, filter)
