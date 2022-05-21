@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.PrivateEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import kotlinx.coroutines.flow.toList

typealias EventMessageToEventMapper<T> = suspend ChatEventMessage<T>.() -> T?

private suspend inline fun <reified O : ChatEvent> BehaviourContext.waitEvents(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<O>>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asBaseSentMessageUpdate() ?.data ?.asChatEventMessage() ?.withEvent<O>() ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        listOf(data.chatEvent)
    } else {
        emptyList()
    }
}.toList().toList()

suspend fun BehaviourContext.waitChannelEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ChannelEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)

suspend fun BehaviourContext.waitPrivateEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<PrivateEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)

suspend fun BehaviourContext.waitChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ChatEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)

@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatStartedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatStarted>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatEndedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEnded>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
@Deprecated("Renamed as Video instead of Voice")
suspend fun BehaviourContext.waitVoiceChatParticipantsInvitedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatParticipantsInvited>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)

suspend fun BehaviourContext.waitVideoChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitVideoChatStartedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatStarted>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitVideoChatEndedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatEnded>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitVideoChatParticipantsInvitedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<VideoChatParticipantsInvited>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)

suspend fun BehaviourContext.waitMessageAutoDeleteTimerChangedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<MessageAutoDeleteTimerChanged>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)


suspend fun BehaviourContext.waitPublicChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<PublicChatEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitCommonEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<CommonEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)

suspend fun BehaviourContext.waitGroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<GroupEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitSupergroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<SupergroupEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)

suspend fun BehaviourContext.waitChannelChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ChannelChatCreated>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitDeleteChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<DeleteChatPhoto>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitGroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<GroupChatCreated>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitLeftChatMemberEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<LeftChatMember>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitNewChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<NewChatPhoto>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitNewChatMembersEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<NewChatMembers>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitNewChatTitleEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<NewChatTitle>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitPinnedMessageEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<PinnedMessage>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitProximityAlertTriggeredEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<ProximityAlertTriggered>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitSupergroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<SupergroupChatCreated>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitSuccessfulPaymentEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<SuccessfulPaymentEvent>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitUserLoggedInEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<ChatEventMessage<UserLoggedIn>>? = null
) = waitEvents(initRequest, errorFactory, count, filter)
suspend fun BehaviourContext.waitWebAppDataEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PrivateEventMessage<WebAppData>>? = null
) = waitEvents(initRequest, errorFactory, count, filter ?.let { SimpleFilter<ChatEventMessage<WebAppData>> { it is PrivateEventMessage && filter(it) } })
