package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asShippingQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import kotlinx.coroutines.flow.toList

typealias ShippingQueryMapper = suspend ShippingQuery.() -> ShippingQuery?

private suspend inline fun <reified O : ShippingQuery> BehaviourContext.waitShippingQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<O>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asShippingQueryUpdate() ?.data as? O ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        listOf(data)
    } else {
        emptyList()
    }
}.toList().toList()


suspend fun BehaviourContext.waitShippingQueries(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ShippingQuery>? = null
) : List<ShippingQuery> = waitShippingQueries(
    count,
    initRequest,
    errorFactory,
    filter
)
