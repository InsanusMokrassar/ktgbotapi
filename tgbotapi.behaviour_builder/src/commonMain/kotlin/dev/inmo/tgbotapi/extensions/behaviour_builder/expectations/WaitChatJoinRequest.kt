package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.ChatJoinRequest
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import kotlinx.coroutines.flow.toList

typealias ChatJoinRequestsMapper = suspend ChatJoinRequest.() -> ChatJoinRequest?

private suspend fun <O> BehaviourContext.waitChatJoinRequests(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<ChatJoinRequest>? = null,
    mapper: suspend ChatJoinRequest.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asChatJoinRequestUpdate() ?.data
    if (data != null && (filter == null || filter(data))) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


suspend fun BehaviourContext.waitChatJoinRequests(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatJoinRequest>? = null,
    mapper: ChatJoinRequestsMapper? = null
) : List<ChatJoinRequest> = waitChatJoinRequests(
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
