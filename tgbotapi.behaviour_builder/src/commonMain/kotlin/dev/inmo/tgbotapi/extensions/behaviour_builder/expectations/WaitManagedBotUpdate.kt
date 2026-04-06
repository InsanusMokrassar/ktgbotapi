package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.managedBotUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.managed_bots.ManagedBotUpdated
import kotlinx.coroutines.flow.Flow

fun BehaviourContext.waitManagedBotUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ManagedBotUpdated> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.managedBotUpdateOrNull() ?.data).let(::listOfNotNull)
}
