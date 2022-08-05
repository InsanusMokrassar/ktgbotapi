package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.chatJoinRequestUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

typealias ChatJoinRequestsMapper = suspend ChatJoinRequest.() -> ChatJoinRequest?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O> BehaviourContext.internalWaitChatJoinRequests(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.chatJoinRequestUpdateOrNull() ?.data as? O).let(::listOfNotNull)
}


suspend fun BehaviourContext.waitChatJoinRequests(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) : Flow<ChatJoinRequest> = internalWaitChatJoinRequests(
    initRequest,
    errorFactory
)
