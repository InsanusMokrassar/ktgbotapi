package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asPreCheckoutQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.asShippingQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import kotlinx.coroutines.flow.toList

typealias PreCheckoutQueryMapper = suspend PreCheckoutQuery.() -> PreCheckoutQuery?

private suspend fun <O> BehaviourContext.waitPreCheckoutQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<PreCheckoutQuery>? = null,
    mapper: suspend PreCheckoutQuery.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asPreCheckoutQueryUpdate() ?.data
    if (data != null && (filter == null || filter(data))) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


suspend fun BehaviourContext.waitPreCheckoutQueries(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PreCheckoutQuery>? = null,
    mapper: PreCheckoutQueryMapper? = null
) : List<PreCheckoutQuery> = waitPreCheckoutQueries(
    count,
    initRequest,
    errorFactory,
    filter
) {
    if (mapper == null) {
        this
    } else {
        mapper(this)
    }
}
