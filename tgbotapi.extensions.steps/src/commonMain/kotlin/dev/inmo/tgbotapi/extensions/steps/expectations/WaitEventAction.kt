@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.steps.expectations

import dev.inmo.tgbotapi.extensions.steps.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.asChatEventMessage
import dev.inmo.tgbotapi.extensions.utils.asMessageUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import kotlinx.coroutines.flow.toList

typealias EventMessageToEventMapper<T> = suspend ChatEventMessage<T>.() -> T?

private suspend fun <O> BehaviourContext.waitEventMessages(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    mapper: suspend ChatEventMessage<ChatEvent>.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    it.asMessageUpdate() ?.data ?.asChatEventMessage() ?.mapper()
}.toList().toList()


private suspend inline fun <reified T : ChatEvent> BehaviourContext.waitEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: EventMessageToEventMapper<T>? = null
) : List<T> = waitEventMessages<T>(
    count,
    initRequest,
    errorFactory
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
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ChannelEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitChatEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ChatEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitCommonEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<CommonEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGroupEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<GroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitSupergroupEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<SupergroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)

suspend fun BehaviourContext.waitChannelChatCreatedEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ChannelChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDeleteChatPhotoEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<DeleteChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGroupChatCreatedEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<GroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitLeftChatMemberEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<LeftChatMember>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitNewChatPhotoEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<NewChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitNewChatMembersEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<NewChatMembers>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitNewChatTitleEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<NewChatTitle>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPinnedMessageEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<PinnedMessage>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitProximityAlertTriggeredEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ProximityAlertTriggered>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitSupergroupChatCreatedEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<SupergroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
