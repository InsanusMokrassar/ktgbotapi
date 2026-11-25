package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.update.WidgetQueryUpdate
import dev.inmo.tgbotapi.types.update.SharedWidgetQueryUpdate
import dev.inmo.tgbotapi.types.widgets.*
import kotlinx.coroutines.flow.Flow

fun BehaviourContext.waitWidgetQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<WidgetQuery> = expectFlow(initRequest, errorFactory) {
    listOfNotNull((it as? WidgetQueryUpdate)?.data)
}

fun BehaviourContext.waitSharedWidgetQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<SharedWidgetQuery> = expectFlow(initRequest, errorFactory) {
    listOfNotNull((it as? SharedWidgetQueryUpdate)?.data)
}
