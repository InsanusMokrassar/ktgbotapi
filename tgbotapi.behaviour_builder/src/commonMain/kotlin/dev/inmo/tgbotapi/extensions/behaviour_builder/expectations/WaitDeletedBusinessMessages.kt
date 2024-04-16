package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
import dev.inmo.tgbotapi.types.business_connection.BusinessMessagesDeleted
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import kotlinx.coroutines.flow.Flow

suspend fun BehaviourContext.waitDeletedBusinessMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<BusinessMessagesDeleted> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.deletedBusinessMessageUpdateOrNull() ?.data).let(::listOfNotNull)
}