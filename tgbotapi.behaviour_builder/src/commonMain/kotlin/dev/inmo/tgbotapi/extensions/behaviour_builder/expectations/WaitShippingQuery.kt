package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.shippingQueryUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import kotlinx.coroutines.flow.Flow

typealias ShippingQueryMapper = suspend ShippingQuery.() -> ShippingQuery?

fun BehaviourContext.waitShippingQueries(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ShippingQuery> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.shippingQueryUpdateOrNull() ?.data).let(::listOfNotNull)
}
