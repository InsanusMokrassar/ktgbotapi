package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.paidMediaPurchasedUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.PaidMediaPayload
import dev.inmo.tgbotapi.types.message.payments.PaidMediaPurchased
import kotlinx.coroutines.flow.Flow

suspend fun BehaviourContext.waitPaidMediaPurchased(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<PaidMediaPurchased> =
    expectFlow(
        initRequest,
        errorFactory,
    ) {
        (it.paidMediaPurchasedUpdateOrNull() ?.data).let(::listOfNotNull)
    }

suspend fun BehaviourContext.waitPaidMediaPurchased(
    paidMediaPayloadRegex: Regex,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<PaidMediaPurchased> =
    expectFlow(
        initRequest,
        errorFactory,
    ) {
        (it.paidMediaPurchasedUpdateOrNull() ?.data ?.takeIf { paidMediaPayloadRegex.matches(it.payload.string) }).let(::listOfNotNull)
    }

suspend fun BehaviourContext.waitPaidMediaPurchased(
    paidMediaPayload: PaidMediaPayload,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<PaidMediaPurchased> =
    expectFlow(
        initRequest,
        errorFactory,
    ) {
        (it.paidMediaPurchasedUpdateOrNull() ?.data ?.takeIf { it.payload == paidMediaPayload }).let(::listOfNotNull)
    }
