@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
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
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import dev.inmo.tgbotapi.types.request.ChatShared
import dev.inmo.tgbotapi.types.request.ChatSharedRequest
import dev.inmo.tgbotapi.types.request.UserShared
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : ChatEvent> BehaviourContext.waitEventsMessages(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatEventMessage<O>> = expectFlow(
    initRequest,
    errorFactory
) {
    it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.withEvent<O>().let(::listOfNotNull)
}

suspend fun BehaviourContext.waitChannelEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChannelEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitPrivateEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PrivateEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitVideoChatEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoChatStartedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatStarted>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoChatEndedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatEnded>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoChatParticipantsInvitedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatParticipantsInvited>(initRequest, errorFactory)

suspend fun BehaviourContext.waitMessageAutoDeleteTimerChangedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<MessageAutoDeleteTimerChanged>(initRequest, errorFactory)


suspend fun BehaviourContext.waitPublicChatEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PublicChatEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitCommonEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<CommonEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitGroupEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GroupEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitSupergroupEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SupergroupEvent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChannelChatCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChannelChatCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDeleteChatPhotoEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<DeleteChatPhoto>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGroupChatCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GroupChatCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitLeftChatMemberEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<LeftChatMemberEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitNewChatPhotoEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatPhoto>(initRequest, errorFactory)
suspend fun BehaviourContext.waitNewChatMembersEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatMembers>(initRequest, errorFactory)
suspend fun BehaviourContext.waitNewChatTitleEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatTitle>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPinnedMessageEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PinnedMessage>(initRequest, errorFactory)
suspend fun BehaviourContext.waitProximityAlertTriggeredEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ProximityAlertTriggered>(initRequest, errorFactory)
suspend fun BehaviourContext.waitSupergroupChatCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SupergroupChatCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitSuccessfulPaymentEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SuccessfulPaymentEvent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitUserLoggedInEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<UserLoggedIn>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWebAppDataEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WebAppData>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicClosedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicClosed>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicCreated>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicReopenedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicReopened>(initRequest, errorFactory)
suspend fun BehaviourContext.waitForumTopicEditedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicEdited>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGeneralForumTopicHiddenEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GeneralForumTopicHidden>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGeneralForumTopicUnhiddenEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GeneralForumTopicUnhidden>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWriteAccessAllowedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWriteAccessAllowedFromRequestEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromRequest>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWriteAccessAllowedFromAttachmentMenuEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromAttachmentMenu>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWriteAccessAllowedFromWebAppLinkEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromWebAppLink>(initRequest, errorFactory)
suspend fun BehaviourContext.waitWriteAccessAllowedOtherEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.Other>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatSharedRequestEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatSharedRequest>(initRequest, errorFactory)

suspend fun BehaviourContext.waitUserSharedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<UserShared>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatSharedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatShared>(initRequest, errorFactory)
