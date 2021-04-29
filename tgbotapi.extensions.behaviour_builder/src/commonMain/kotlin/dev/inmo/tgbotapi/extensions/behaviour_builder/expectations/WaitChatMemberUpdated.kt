package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate
import kotlinx.coroutines.flow.toList

typealias ChatMemberUpdatedMapper<T> = suspend T.() -> T?

private suspend inline fun <reified T : ChatMemberUpdatedUpdate> BehaviourContext.waitChatMemberUpdated(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline mapper: ChatMemberUpdatedMapper<ChatMemberUpdated>
): List<ChatMemberUpdated> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    (it as? T) ?.data.let(::listOfNotNull)
}.toList().toList()

private suspend inline fun <reified T : ChatMemberUpdatedUpdate> BehaviourContext.waitChatMemberUpdatedWithFilter(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) : List<ChatMemberUpdated> = waitChatMemberUpdated<T>(
    count,
    initRequest,
    errorFactory
) {
    if (filter == null) {
        this
    } else {
        filter(this)
    }
}

suspend fun BehaviourContext.waitChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) = waitChatMemberUpdatedWithFilter<ChatMemberUpdatedUpdate>(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitCommonChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) = waitChatMemberUpdatedWithFilter<CommonChatMemberUpdatedUpdate>(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitMyChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) = waitChatMemberUpdatedWithFilter<MyChatMemberUpdatedUpdate>(count, initRequest, errorFactory, filter)
