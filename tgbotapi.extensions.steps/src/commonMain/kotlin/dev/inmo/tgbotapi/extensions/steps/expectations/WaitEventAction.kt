@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.steps.expectations

import dev.inmo.tgbotapi.extensions.steps.Scenario
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.content.ContactContent
import kotlinx.coroutines.flow.toList

typealias EventMessageToEventMapper<T> = suspend ChatEventMessage<T>.() -> T?

private suspend fun <O> Scenario.waitEventMessages(
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


private suspend inline fun <reified T : ChatEvent> Scenario.waitEvents(
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

suspend fun Scenario.waitChannelEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ChannelEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)

suspend fun Scenario.waitChatEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ChatEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitCommonEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<CommonEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitGroupEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<GroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitSupergroupEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<SupergroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter)

suspend fun Scenario.waitChannelChatCreatedEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ChannelChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitDeleteChatPhotoEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<DeleteChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitGroupChatCreatedEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<GroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitLeftChatMemberEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<LeftChatMember>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitNewChatPhotoEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<NewChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitNewChatMembersEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<NewChatMembers>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitNewChatTitleEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<NewChatTitle>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitPinnedMessageEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<PinnedMessage>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitProximityAlertTriggeredEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<ProximityAlertTriggered>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitSupergroupChatCreatedEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: EventMessageToEventMapper<SupergroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
