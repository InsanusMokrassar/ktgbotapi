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
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatEventMessage<O>> = expectFlow(
    errorFactory
) {
    it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.withEvent<O>().let(::listOfNotNull)
}

fun BehaviourContext.waitChannelEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChannelEvent>(errorFactory)

fun BehaviourContext.waitPrivateEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PrivateEvent>(errorFactory)

fun BehaviourContext.waitChatEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatEvent>(errorFactory)

fun BehaviourContext.waitVideoChatEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatEvent>(errorFactory)
fun BehaviourContext.waitVideoChatStartedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatStarted>(errorFactory)
fun BehaviourContext.waitVideoChatEndedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatEnded>(errorFactory)
fun BehaviourContext.waitVideoChatParticipantsInvitedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<VideoChatParticipantsInvited>(errorFactory)

fun BehaviourContext.waitMessageAutoDeleteTimerChangedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<MessageAutoDeleteTimerChanged>(errorFactory)


fun BehaviourContext.waitPublicChatEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PublicChatEvent>(errorFactory)
fun BehaviourContext.waitCommonEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<CommonEvent>(errorFactory)

fun BehaviourContext.waitGroupEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GroupEvent>(errorFactory)
fun BehaviourContext.waitSupergroupEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SupergroupEvent>(errorFactory)

fun BehaviourContext.waitChannelChatCreatedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChannelChatCreated>(errorFactory)
fun BehaviourContext.waitDeleteChatPhotoEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<DeleteChatPhoto>(errorFactory)
fun BehaviourContext.waitGroupChatCreatedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GroupChatCreated>(errorFactory)
fun BehaviourContext.waitLeftChatMemberEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<LeftChatMemberEvent>(errorFactory)
fun BehaviourContext.waitNewChatPhotoEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatPhoto>(errorFactory)
fun BehaviourContext.waitNewChatMembersEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatMembers>(errorFactory)
fun BehaviourContext.waitNewChatTitleEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<NewChatTitle>(errorFactory)
fun BehaviourContext.waitPinnedMessageEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PinnedMessage>(errorFactory)
fun BehaviourContext.waitProximityAlertTriggeredEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ProximityAlertTriggered>(errorFactory)
fun BehaviourContext.waitSupergroupChatCreatedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SupergroupChatCreated>(errorFactory)
fun BehaviourContext.waitSuccessfulPaymentEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<SuccessfulPaymentEvent>(errorFactory)
fun BehaviourContext.waitRefundedPaymentEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<RefundedPaymentEvent>(errorFactory)
fun BehaviourContext.waitUserLoggedInEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<UserLoggedIn>(errorFactory)
fun BehaviourContext.waitWebAppDataEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WebAppData>(errorFactory)
fun BehaviourContext.waitForumTopicClosedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicClosed>(errorFactory)
fun BehaviourContext.waitForumTopicCreatedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicCreated>(errorFactory)
fun BehaviourContext.waitForumTopicReopenedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicReopened>(errorFactory)
fun BehaviourContext.waitForumTopicEditedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ForumTopicEdited>(errorFactory)
fun BehaviourContext.waitGeneralForumTopicHiddenEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GeneralForumTopicHidden>(errorFactory)
fun BehaviourContext.waitGeneralForumTopicUnhiddenEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GeneralForumTopicUnhidden>(errorFactory)
fun BehaviourContext.waitWriteAccessAllowedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed>(errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromRequestEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromRequest>(errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromAttachmentMenuEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromAttachmentMenu>(errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromWebAppLinkEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.FromWebAppLink>(errorFactory)
fun BehaviourContext.waitWriteAccessAllowedOtherEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<WriteAccessAllowed.Other>(errorFactory)

fun BehaviourContext.waitChatSharedRequestEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatSharedRequest>(errorFactory)

fun BehaviourContext.waitUsersSharedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<UsersShared>(errorFactory)

fun BehaviourContext.waitUserSharedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitUsersSharedEventsMessages(errorFactory).filter { it.chatEvent.userIds.size == 1 }

fun BehaviourContext.waitChatSharedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatShared>(errorFactory)

fun BehaviourContext.waitChatBoostAddedEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatBoostAdded>(errorFactory)

fun BehaviourContext.waitChatBackgroundSetEventsMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<ChatBackground>(errorFactory)

fun BehaviourContext.waitPaidMessagePriceChangedMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<PaidMessagePriceChanged>(errorFactory)

fun BehaviourContext.waitRegularGiftSentOrReceivedMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GiftSentOrReceived.Regular>(errorFactory)

fun BehaviourContext.waitUniqueGiftSentOrReceivedMessages(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEventsMessages<GiftSentOrReceived.Unique>(errorFactory)
