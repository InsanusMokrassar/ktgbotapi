@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.PaidMessagePriceChanged
import dev.inmo.tgbotapi.types.chat.ChatBackground
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
import dev.inmo.tgbotapi.types.giveaway.GiveawayCreated
import dev.inmo.tgbotapi.types.giveaway.GiveawayPrivateResults
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
inline fun <reified O : ChatEvent> BehaviourContext.waitEvents(
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    errorFactory
) {
    it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.withEvent<O>() ?.chatEvent.let(::listOfNotNull)
}

fun BehaviourContext.waitChannelEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChannelEvent>(errorFactory)

fun BehaviourContext.waitPrivateEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PrivateEvent>(errorFactory)

fun BehaviourContext.waitChatEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatEvent>(errorFactory)


fun BehaviourContext.waitVideoChatEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatEvent>(errorFactory)
fun BehaviourContext.waitVideoChatStartedEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatStarted>(errorFactory)
fun BehaviourContext.waitVideoChatEndedEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatEnded>(errorFactory)
fun BehaviourContext.waitVideoChatParticipantsInvitedEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatParticipantsInvited>(errorFactory)

fun BehaviourContext.waitMessageAutoDeleteTimerChangedEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<MessageAutoDeleteTimerChanged>(errorFactory)


fun BehaviourContext.waitPublicChatEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PublicChatEvent>(errorFactory)
fun BehaviourContext.waitCommonEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<CommonEvent>(errorFactory)

fun BehaviourContext.waitGroupEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GroupEvent>(errorFactory)
fun BehaviourContext.waitSupergroupEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SupergroupEvent>(errorFactory)

fun BehaviourContext.waitChannelChatCreatedEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChannelChatCreated>(errorFactory)
fun BehaviourContext.waitDeleteChatPhotoEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<DeleteChatPhoto>(errorFactory)
fun BehaviourContext.waitGroupChatCreatedEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GroupChatCreated>(errorFactory)
fun BehaviourContext.waitLeftChatMemberEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<LeftChatMemberEvent>(errorFactory)
fun BehaviourContext.waitNewChatPhotoEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatPhoto>(errorFactory)
fun BehaviourContext.waitNewChatMembersEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatMembers>(errorFactory)
fun BehaviourContext.waitNewChatTitleEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatTitle>(errorFactory)
fun BehaviourContext.waitPinnedMessageEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PinnedMessage>(errorFactory)
fun BehaviourContext.waitProximityAlertTriggeredEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ProximityAlertTriggered>(errorFactory)
fun BehaviourContext.waitSupergroupChatCreatedEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SupergroupChatCreated>(errorFactory)
fun BehaviourContext.waitSuccessfulPaymentEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuccessfulPaymentEvent>(errorFactory)
fun BehaviourContext.waitRefundedPaymentEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<RefundedPaymentEvent>(errorFactory)
fun BehaviourContext.waitUserLoggedInEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<UserLoggedIn>(errorFactory)
fun BehaviourContext.waitWebAppDataEvents(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WebAppData>(errorFactory)
fun BehaviourContext.waitForumTopicClosed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicClosed>(errorFactory)
fun BehaviourContext.waitForumTopicCreated(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicCreated>(errorFactory)
fun BehaviourContext.waitForumTopicReopened(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicReopened>(errorFactory)
fun BehaviourContext.waitForumTopicEdited(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicEdited>(errorFactory)
fun BehaviourContext.waitGeneralForumTopicHidden(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GeneralForumTopicHidden>(errorFactory)
fun BehaviourContext.waitGeneralForumTopicUnhidden(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GeneralForumTopicUnhidden>(errorFactory)
fun BehaviourContext.waitWriteAccessAllowed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed>(errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromRequest(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromRequest>(errorFactory)

fun BehaviourContext.waitWriteAccessAllowedFromAttachmentMenu(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromAttachmentMenu>(errorFactory)

fun BehaviourContext.waitWriteAccessAllowedFromWebAppLink(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromWebAppLink>(errorFactory)

fun BehaviourContext.waitWriteAccessAllowedOther(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.Other>(errorFactory)

fun BehaviourContext.waitChatSharedRequest(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatSharedRequest>(errorFactory)

fun BehaviourContext.waitUsersShared(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<UsersShared>(errorFactory)

fun BehaviourContext.waitUserShared(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitUsersShared(errorFactory).filter { it.userIds.size == 1 }

fun BehaviourContext.waitChatShared(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatShared>(errorFactory)

fun BehaviourContext.waitChatBoostAdded(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatBoostAdded>(errorFactory)

fun BehaviourContext.waitChatBackgroundSet(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatBackground>(errorFactory)

fun BehaviourContext.waitGiveawayCreated(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiveawayCreated>(errorFactory)

fun BehaviourContext.waitGiveawayCompleted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiveawayPrivateResults>(errorFactory)

fun BehaviourContext.waitGiveawayCompletedWithPrivateWinners(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayCompleted(errorFactory)

fun BehaviourContext.waitPaidMessagePriceChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PaidMessagePriceChanged>(errorFactory)

fun BehaviourContext.waitRegularGiftSentOrReceived(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiftSentOrReceived.Regular>(errorFactory)

fun BehaviourContext.waitUniqueGiftSentOrReceived(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiftSentOrReceived.Unique>(errorFactory)
