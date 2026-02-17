@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.PaidMessagePriceChanged
import dev.inmo.tgbotapi.types.chat.ChatBackground
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
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

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified O : ChatEvent> BehaviourContext.waitEventsMessages(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatEventMessage<O>> = expectFlow(
    initRequest,
    errorFactory
) {
    it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.withEvent<O>().let(::listOfNotNull)
}

fun BehaviourContext.waitChannelEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChannelEvent>(initRequest, errorFactory)

fun BehaviourContext.waitPrivateEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PrivateEvent>(initRequest, errorFactory)

fun BehaviourContext.waitChatEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatEvent>(initRequest, errorFactory)

fun BehaviourContext.waitVideoChatEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatEvent>(initRequest, errorFactory)
fun BehaviourContext.waitVideoChatStartedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatStarted>(initRequest, errorFactory)
fun BehaviourContext.waitVideoChatEndedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatEnded>(initRequest, errorFactory)
fun BehaviourContext.waitVideoChatParticipantsInvitedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatParticipantsInvited>(initRequest, errorFactory)

fun BehaviourContext.waitMessageAutoDeleteTimerChangedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<MessageAutoDeleteTimerChanged>(initRequest, errorFactory)


fun BehaviourContext.waitPublicChatEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PublicChatEvent>(initRequest, errorFactory)
fun BehaviourContext.waitCommonEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<CommonEvent>(initRequest, errorFactory)

fun BehaviourContext.waitGroupEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GroupEvent>(initRequest, errorFactory)
fun BehaviourContext.waitSupergroupEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SupergroupEvent>(initRequest, errorFactory)

fun BehaviourContext.waitChannelChatCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChannelChatCreated>(initRequest, errorFactory)
fun BehaviourContext.waitDeleteChatPhotoEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<DeleteChatPhoto>(initRequest, errorFactory)
fun BehaviourContext.waitGroupChatCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GroupChatCreated>(initRequest, errorFactory)
fun BehaviourContext.waitLeftChatMemberEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<LeftChatMemberEvent>(initRequest, errorFactory)
fun BehaviourContext.waitNewChatPhotoEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatPhoto>(initRequest, errorFactory)
fun BehaviourContext.waitNewChatMembersEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatMembers>(initRequest, errorFactory)
fun BehaviourContext.waitNewChatTitleEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatTitle>(initRequest, errorFactory)
fun BehaviourContext.waitPinnedMessageEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PinnedMessage>(initRequest, errorFactory)
fun BehaviourContext.waitProximityAlertTriggeredEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ProximityAlertTriggered>(initRequest, errorFactory)
fun BehaviourContext.waitSupergroupChatCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SupergroupChatCreated>(initRequest, errorFactory)
fun BehaviourContext.waitSuccessfulPaymentEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SuccessfulPaymentEvent>(initRequest, errorFactory)
fun BehaviourContext.waitRefundedPaymentEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<RefundedPaymentEvent>(initRequest, errorFactory)
fun BehaviourContext.waitUserLoggedInEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<UserLoggedIn>(initRequest, errorFactory)
fun BehaviourContext.waitWebAppDataEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WebAppData>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicClosedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicClosed>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicCreatedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicCreated>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicReopenedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicReopened>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicEditedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicEdited>(initRequest, errorFactory)
fun BehaviourContext.waitGeneralForumTopicHiddenEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GeneralForumTopicHidden>(initRequest, errorFactory)
fun BehaviourContext.waitGeneralForumTopicUnhiddenEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GeneralForumTopicUnhidden>(initRequest, errorFactory)
fun BehaviourContext.waitWriteAccessAllowedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed>(initRequest, errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromRequestEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromRequest>(initRequest, errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromAttachmentMenuEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromAttachmentMenu>(initRequest, errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromWebAppLinkEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromWebAppLink>(initRequest, errorFactory)
fun BehaviourContext.waitWriteAccessAllowedOtherEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.Other>(initRequest, errorFactory)

fun BehaviourContext.waitChatSharedRequestEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatSharedRequest>(initRequest, errorFactory)

fun BehaviourContext.waitUsersSharedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<UsersShared>(initRequest, errorFactory)

fun BehaviourContext.waitUserSharedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitUsersSharedEventsMessages(initRequest, errorFactory).filter { it.chatEvent.userIds.size == 1 }

fun BehaviourContext.waitChatSharedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatShared>(initRequest, errorFactory)

fun BehaviourContext.waitChatBoostAddedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatBoostAdded>(initRequest, errorFactory)

fun BehaviourContext.waitChatOwnerLeftEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatOwnerLeft>(initRequest, errorFactory)

fun BehaviourContext.waitChatOwnerChangedEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatOwnerChanged>(initRequest, errorFactory)

fun BehaviourContext.waitChatBackgroundSetEventsMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatBackground>(initRequest, errorFactory)

fun BehaviourContext.waitPaidMessagePriceChangedMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PaidMessagePriceChanged>(initRequest, errorFactory)

fun BehaviourContext.waitRegularGiftSentOrReceivedMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GiftSentOrReceived.Regular>(initRequest, errorFactory)

fun BehaviourContext.waitUniqueGiftSentOrReceivedMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GiftSentOrReceived.Unique>(initRequest, errorFactory)
