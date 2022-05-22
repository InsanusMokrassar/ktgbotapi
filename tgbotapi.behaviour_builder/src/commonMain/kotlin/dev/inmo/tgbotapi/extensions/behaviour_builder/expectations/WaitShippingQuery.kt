package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asShippingQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

typealias ShippingQueryMapper = suspend ShippingQuery.() -> ShippingQuery?

suspend fun BehaviourContext.waitShippingQueries(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ShippingQuery> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.asShippingQueryUpdate() ?.data).let(::listOfNotNull)
}
