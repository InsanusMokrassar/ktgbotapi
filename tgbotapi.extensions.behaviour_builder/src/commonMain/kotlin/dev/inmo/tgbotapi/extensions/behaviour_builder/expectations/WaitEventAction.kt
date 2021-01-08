@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.asChatEventMessage
import dev.inmo.tgbotapi.extensions.utils.asMessageUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import kotlinx.coroutines.flow.toList

typealias EventMessageToEventMapper<T> = suspend ChatEventMessage<T>.() -> T?

private suspend fun <O> BehaviourContext.waitEventMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    mapper: suspend ChatEventMessage<ChatEvent>.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    it.asMessageUpdate() ?.data ?.asChatEventMessage() ?.mapper().let(::listOfNotNull)
}.toList().toList()


private suspend inline fun <reified T : ChatEvent> BehaviourContext.waitEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: EventMessageToEventMapper<T>? = null
) : List<T> = waitEventMessages<T>(
    initRequest,
    errorFactory,
    count
) {
    if (chatEvent is T) {
        @Suppress("UNCHECKED_CAST")
        val message = (this as ChatEventMessage<T>)
        if (filter == null) {
            message.chatEvent
        } else {
            filter(message)
        }
    } else {
        null
    }
}

suspend fun BehaviourContext.waitChannelEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<ChannelEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<ChatEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitCommonEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<CommonEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<GroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitSupergroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<SupergroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitChannelChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<ChannelChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDeleteChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<DeleteChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<GroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitLeftChatMemberEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<LeftChatMember>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitNewChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<NewChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitNewChatMembersEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<NewChatMembers>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitNewChatTitleEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<NewChatTitle>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPinnedMessageEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<PinnedMessage>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitProximityAlertTriggeredEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<ProximityAlertTriggered>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitSupergroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: EventMessageToEventMapper<SupergroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
