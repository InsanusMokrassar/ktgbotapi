@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.chat.ChatBackground
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicClosed
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicCreated
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicEdited
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.ForumTopicReopened
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicHidden
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.GeneralForumTopicUnhidden
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.WriteAccessAllowed
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.payments.RefundedPaymentEvent
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import dev.inmo.tgbotapi.types.request.ChatShared
import dev.inmo.tgbotapi.types.request.ChatSharedRequest
import dev.inmo.tgbotapi.types.request.UsersShared
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

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
) = waitEvents<LeftChatMemberEvent>(initRequest, errorFactory)
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
suspend fun BehaviourContext.waitRefundedPaymentEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<RefundedPaymentEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitUserLoggedInEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<UserLoggedIn>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWebAppDataEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WebAppData>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicClosed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicClosed>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicCreated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicReopened(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicReopened>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicEdited(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicEdited>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGeneralForumTopicHidden(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GeneralForumTopicHidden>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGeneralForumTopicUnhidden(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GeneralForumTopicUnhidden>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWriteAccessAllowed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWriteAccessAllowedFromRequest(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromRequest>(initRequest, errorFactory)

suspend fun BehaviourContext.waitWriteAccessAllowedFromAttachmentMenu(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromAttachmentMenu>(initRequest, errorFactory)

suspend fun BehaviourContext.waitWriteAccessAllowedFromWebAppLink(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromWebAppLink>(initRequest, errorFactory)

suspend fun BehaviourContext.waitWriteAccessAllowedOther(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.Other>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatSharedRequest(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatSharedRequest>(initRequest, errorFactory)

suspend fun BehaviourContext.waitUsersShared(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<UsersShared>(initRequest, errorFactory)

suspend fun BehaviourContext.waitUserShared(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitUsersShared(initRequest, errorFactory).filter { it.userIds.size == 1 }

suspend fun BehaviourContext.waitChatShared(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatShared>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatBoostAdded(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatBoostAdded>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatBackgroundSet(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatBackground>(initRequest, errorFactory)
