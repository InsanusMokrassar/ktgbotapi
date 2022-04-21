package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
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
    filter: SimpleFilter<T>? = null,
    noinline mapper: ChatMemberUpdatedMapper<ChatMemberUpdated>
): List<ChatMemberUpdated> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val casted = (it as? T) ?: return@expectFlow emptyList()
    if (filter == null || filter(casted)) {
        casted.data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()

private suspend inline fun <reified T : ChatMemberUpdatedUpdate> BehaviourContext.waitChatMemberUpdatedWithFilter(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<T>? = null,
    noinline mapper: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) : List<ChatMemberUpdated> = waitChatMemberUpdated<T>(
    count,
    initRequest,
    errorFactory,
    filter,
) {
    if (mapper == null) {
        this
    } else {
        mapper(this)
    }
}

suspend fun BehaviourContext.waitChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatMemberUpdatedUpdate>? = null,
    mapper: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) = waitChatMemberUpdatedWithFilter<ChatMemberUpdatedUpdate>(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitCommonChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonChatMemberUpdatedUpdate>? = null,
    mapper: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) = waitChatMemberUpdatedWithFilter<CommonChatMemberUpdatedUpdate>(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitMyChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<MyChatMemberUpdatedUpdate>? = null,
    mapper: ChatMemberUpdatedMapper<ChatMemberUpdated>? = null
) = waitChatMemberUpdatedWithFilter<MyChatMemberUpdatedUpdate>(count, initRequest, errorFactory, filter, mapper)
