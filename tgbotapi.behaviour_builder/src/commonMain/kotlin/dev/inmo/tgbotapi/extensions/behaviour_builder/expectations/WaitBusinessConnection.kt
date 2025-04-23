package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.businessConnectionUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.disabledOrNull
import dev.inmo.tgbotapi.extensions.utils.enabledOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
import kotlinx.coroutines.flow.Flow

suspend fun BehaviourContext.waitBusinessConnection(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<BusinessConnection> = expectFlow(
    initRequest,
    errorFactory,
) {
    (it.businessConnectionUpdateOrNull() ?.data).let(::listOfNotNull)
}

suspend fun BehaviourContext.waitBusinessConnectionEnabled(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<BusinessConnection> = expectFlow(
    initRequest,
    errorFactory,
) {
    (it.businessConnectionUpdateOrNull() ?.data ?.enabledOrNull()).let(::listOfNotNull)
}

suspend fun BehaviourContext.waitBusinessConnectionDisabled(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<BusinessConnection> = expectFlow(
    initRequest,
    errorFactory,
) {
    (it.businessConnectionUpdateOrNull() ?.data ?.disabledOrNull()).let(::listOfNotNull)
}
