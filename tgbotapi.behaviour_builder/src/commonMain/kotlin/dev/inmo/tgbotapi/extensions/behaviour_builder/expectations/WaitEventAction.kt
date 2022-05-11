@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asBaseSentMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asChatEventMessage
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.PrivateEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import kotlinx.coroutines.flow.toList

typealias EventMessageToEventMapper<T> = suspend ChatEventMessage<T>.() -> T?

private suspend fun <O> BehaviourContext.waitEventMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ChatEvent>>? = null,
    mapper: suspend ChatEventMessage<ChatEvent>.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asBaseSentMessageUpdate() ?.data ?.asChatEventMessage()
    if (data != null && (filter == null || filter(data))) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


private suspend inline fun <reified T : ChatEvent> BehaviourContext.waitEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<ChatEventMessage<T>>? = null,
    noinline mapper: EventMessageToEventMapper<T>? = null
) : List<T> = waitEventMessages<T>(
    initRequest,
    errorFactory,
    count,
    filter ?.let {
        {
            (it.chatEvent as? T) ?.let { filter(it as ChatEventMessage<T>) } == true
        }
    }
) {
    if (chatEvent is T) {
        @Suppress("UNCHECKED_CAST")
        val message = (this as ChatEventMessage<T>)
        if (mapper == null) {
            message.chatEvent
        } else {
            mapper(message)
        }
    } else {
        null
    }
}

suspend fun BehaviourContext.waitChannelEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ChannelEvent>>? = null,
    mapper: EventMessageToEventMapper<ChannelEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitPrivateEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<PrivateEvent>>? = null,
    mapper: EventMessageToEventMapper<PrivateEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ChatEvent>>? = null,
    mapper: EventMessageToEventMapper<ChatEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)

@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEvent>>? = null,
    mapper: EventMessageToEventMapper<VideoChatEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatStartedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatStarted>>? = null,
    mapper: EventMessageToEventMapper<VideoChatStarted>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatEndedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEnded>>? = null,
    mapper: EventMessageToEventMapper<VideoChatEnded>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatParticipantsInvitedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatParticipantsInvited>>? = null,
    mapper: EventMessageToEventMapper<VideoChatParticipantsInvited>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitVideoChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEvent>>? = null,
    mapper: EventMessageToEventMapper<VideoChatEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVideoChatStartedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatStarted>>? = null,
    mapper: EventMessageToEventMapper<VideoChatStarted>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVideoChatEndedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEnded>>? = null,
    mapper: EventMessageToEventMapper<VideoChatEnded>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVideoChatParticipantsInvitedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatParticipantsInvited>>? = null,
    mapper: EventMessageToEventMapper<VideoChatParticipantsInvited>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitMessageAutoDeleteTimerChangedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<MessageAutoDeleteTimerChanged>>? = null,
    mapper: EventMessageToEventMapper<MessageAutoDeleteTimerChanged>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)


suspend fun BehaviourContext.waitPublicChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<PublicChatEvent>>? = null,
    mapper: EventMessageToEventMapper<PublicChatEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitCommonEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<CommonEvent>>? = null,
    mapper: EventMessageToEventMapper<CommonEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitGroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<GroupEvent>>? = null,
    mapper: EventMessageToEventMapper<GroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitSupergroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<SupergroupEvent>>? = null,
    mapper: EventMessageToEventMapper<SupergroupEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)

suspend fun BehaviourContext.waitChannelChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ChannelChatCreated>>? = null,
    mapper: EventMessageToEventMapper<ChannelChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitDeleteChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<DeleteChatPhoto>>? = null,
    mapper: EventMessageToEventMapper<DeleteChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitGroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<GroupChatCreated>>? = null,
    mapper: EventMessageToEventMapper<GroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitLeftChatMemberEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<LeftChatMember>>? = null,
    mapper: EventMessageToEventMapper<LeftChatMember>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitNewChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<NewChatPhoto>>? = null,
    mapper: EventMessageToEventMapper<NewChatPhoto>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitNewChatMembersEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<NewChatMembers>>? = null,
    mapper: EventMessageToEventMapper<NewChatMembers>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitNewChatTitleEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<NewChatTitle>>? = null,
    mapper: EventMessageToEventMapper<NewChatTitle>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitPinnedMessageEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<PinnedMessage>>? = null,
    mapper: EventMessageToEventMapper<PinnedMessage>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitProximityAlertTriggeredEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ProximityAlertTriggered>>? = null,
    mapper: EventMessageToEventMapper<ProximityAlertTriggered>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitSupergroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<SupergroupChatCreated>>? = null,
    mapper: EventMessageToEventMapper<SupergroupChatCreated>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitSuccessfulPaymentEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<SuccessfulPaymentEvent>>? = null,
    mapper: EventMessageToEventMapper<SuccessfulPaymentEvent>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitUserLoggedInEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<UserLoggedIn>>? = null,
    mapper: EventMessageToEventMapper<UserLoggedIn>? = null
) = waitEvents(count, initRequest, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitWebAppDataEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PrivateEventMessage<WebAppData>>? = null,
    mapper: EventMessageToEventMapper<WebAppData>? = null
) = waitEvents(count, initRequest, errorFactory, filter ?.let { { it is PrivateEventMessage && filter(it) } }, mapper)
