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
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

typealias EventMessageToEventMapper<T> = suspend ChatEventMessage<T>.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : ChatEvent> BehaviourContext.waitEvents(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.withEvent<O>() ?.chatEvent.let(::listOfNotNull)
}

suspend fun BehaviourContext.waitChannelEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChannelEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitPrivateEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PrivateEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatEvent>(initRequest, errorFactory)


suspend fun BehaviourContext.waitVideoChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoChatStartedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatStarted>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoChatEndedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatEnded>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoChatParticipantsInvitedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatParticipantsInvited>(initRequest, errorFactory)

suspend fun BehaviourContext.waitMessageAutoDeleteTimerChangedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<MessageAutoDeleteTimerChanged>(initRequest, errorFactory)


suspend fun BehaviourContext.waitPublicChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PublicChatEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitCommonEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<CommonEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitGroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GroupEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitSupergroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SupergroupEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChannelChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChannelChatCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDeleteChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<DeleteChatPhoto>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GroupChatCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitLeftChatMemberEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<LeftChatMember>(initRequest, errorFactory)
suspend fun BehaviourContext.waitNewChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatPhoto>(initRequest, errorFactory)
suspend fun BehaviourContext.waitNewChatMembersEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatMembers>(initRequest, errorFactory)
suspend fun BehaviourContext.waitNewChatTitleEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatTitle>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPinnedMessageEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PinnedMessage>(initRequest, errorFactory)
suspend fun BehaviourContext.waitProximityAlertTriggeredEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ProximityAlertTriggered>(initRequest, errorFactory)
suspend fun BehaviourContext.waitSupergroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SupergroupChatCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitSuccessfulPaymentEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuccessfulPaymentEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitUserLoggedInEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<UserLoggedIn>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWebAppDataEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WebAppData>(initRequest, errorFactory)
