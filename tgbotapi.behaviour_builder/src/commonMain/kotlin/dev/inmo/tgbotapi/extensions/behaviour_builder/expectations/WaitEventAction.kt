@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.DirectMessagesConfigurationChanged
import dev.inmo.tgbotapi.types.PaidMessagePriceChanged
import dev.inmo.tgbotapi.types.chat.ChatBackground
import dev.inmo.tgbotapi.types.checklists.ChecklistTasksAdded
import dev.inmo.tgbotapi.types.checklists.ChecklistTasksDone
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
import dev.inmo.tgbotapi.types.message.ChatEvents.suggested.SuggestedPostApprovalFailed
import dev.inmo.tgbotapi.types.message.ChatEvents.suggested.SuggestedPostApproved
import dev.inmo.tgbotapi.types.message.ChatEvents.suggested.SuggestedPostDeclined
import dev.inmo.tgbotapi.types.message.ChatEvents.suggested.SuggestedPostInfo
import dev.inmo.tgbotapi.types.message.ChatEvents.suggested.SuggestedPostPaid
import dev.inmo.tgbotapi.types.message.ChatEvents.suggested.SuggestedPostRefunded
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
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.withEvent<O>() ?.chatEvent.let(::listOfNotNull)
}

fun BehaviourContext.waitChannelEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChannelEvent>(initRequest, errorFactory)

fun BehaviourContext.waitPrivateEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PrivateEvent>(initRequest, errorFactory)

fun BehaviourContext.waitChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatEvent>(initRequest, errorFactory)


fun BehaviourContext.waitVideoChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatEvent>(initRequest, errorFactory)
fun BehaviourContext.waitVideoChatStartedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatStarted>(initRequest, errorFactory)
fun BehaviourContext.waitVideoChatEndedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatEnded>(initRequest, errorFactory)
fun BehaviourContext.waitVideoChatParticipantsInvitedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<VideoChatParticipantsInvited>(initRequest, errorFactory)

fun BehaviourContext.waitMessageAutoDeleteTimerChangedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<MessageAutoDeleteTimerChanged>(initRequest, errorFactory)


fun BehaviourContext.waitPublicChatEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PublicChatEvent>(initRequest, errorFactory)
fun BehaviourContext.waitCommonEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<CommonEvent>(initRequest, errorFactory)

fun BehaviourContext.waitGroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GroupEvent>(initRequest, errorFactory)
fun BehaviourContext.waitSupergroupEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SupergroupEvent>(initRequest, errorFactory)

fun BehaviourContext.waitChannelChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChannelChatCreated>(initRequest, errorFactory)
fun BehaviourContext.waitDeleteChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<DeleteChatPhoto>(initRequest, errorFactory)
fun BehaviourContext.waitGroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GroupChatCreated>(initRequest, errorFactory)
fun BehaviourContext.waitLeftChatMemberEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<LeftChatMemberEvent>(initRequest, errorFactory)
fun BehaviourContext.waitNewChatPhotoEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatPhoto>(initRequest, errorFactory)
fun BehaviourContext.waitNewChatMembersEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatMembers>(initRequest, errorFactory)
fun BehaviourContext.waitNewChatTitleEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<NewChatTitle>(initRequest, errorFactory)
fun BehaviourContext.waitPinnedMessageEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PinnedMessage>(initRequest, errorFactory)
fun BehaviourContext.waitProximityAlertTriggeredEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ProximityAlertTriggered>(initRequest, errorFactory)
fun BehaviourContext.waitSupergroupChatCreatedEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SupergroupChatCreated>(initRequest, errorFactory)
fun BehaviourContext.waitSuccessfulPaymentEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuccessfulPaymentEvent>(initRequest, errorFactory)
fun BehaviourContext.waitRefundedPaymentEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<RefundedPaymentEvent>(initRequest, errorFactory)
fun BehaviourContext.waitUserLoggedInEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<UserLoggedIn>(initRequest, errorFactory)
fun BehaviourContext.waitWebAppDataEvents(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WebAppData>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicClosed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicClosed>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicCreated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicCreated>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicReopened(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicReopened>(initRequest, errorFactory)
fun BehaviourContext.waitForumTopicEdited(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ForumTopicEdited>(initRequest, errorFactory)
fun BehaviourContext.waitGeneralForumTopicHidden(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GeneralForumTopicHidden>(initRequest, errorFactory)
fun BehaviourContext.waitGeneralForumTopicUnhidden(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GeneralForumTopicUnhidden>(initRequest, errorFactory)
fun BehaviourContext.waitWriteAccessAllowed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed>(initRequest, errorFactory)
fun BehaviourContext.waitWriteAccessAllowedFromRequest(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromRequest>(initRequest, errorFactory)

fun BehaviourContext.waitWriteAccessAllowedFromAttachmentMenu(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromAttachmentMenu>(initRequest, errorFactory)

fun BehaviourContext.waitWriteAccessAllowedFromWebAppLink(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.FromWebAppLink>(initRequest, errorFactory)

fun BehaviourContext.waitWriteAccessAllowedOther(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<WriteAccessAllowed.Other>(initRequest, errorFactory)

fun BehaviourContext.waitChatSharedRequest(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatSharedRequest>(initRequest, errorFactory)

fun BehaviourContext.waitUsersShared(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<UsersShared>(initRequest, errorFactory)

fun BehaviourContext.waitUserShared(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitUsersShared(initRequest, errorFactory).filter { it.userIds.size == 1 }

fun BehaviourContext.waitChatShared(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatShared>(initRequest, errorFactory)

fun BehaviourContext.waitChatBoostAdded(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatBoostAdded>(initRequest, errorFactory)

fun BehaviourContext.waitChatBackgroundSet(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChatBackground>(initRequest, errorFactory)

fun BehaviourContext.waitGiveawayCreated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiveawayCreated>(initRequest, errorFactory)

fun BehaviourContext.waitGiveawayCompleted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiveawayPrivateResults>(initRequest, errorFactory)

fun BehaviourContext.waitGiveawayCompletedWithPrivateWinners(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayCompleted(initRequest, errorFactory)

fun BehaviourContext.waitPaidMessagePriceChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<PaidMessagePriceChanged>(initRequest, errorFactory)

fun BehaviourContext.waitRegularGiftSentOrReceived(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiftSentOrReceived.Regular>(initRequest, errorFactory)

fun BehaviourContext.waitUniqueGiftSentOrReceived(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<GiftSentOrReceived.Unique>(initRequest, errorFactory)

fun BehaviourContext.waitChecklistTasksDone(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChecklistTasksDone>(initRequest, errorFactory)

fun BehaviourContext.waitChecklistTasksAdded(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<ChecklistTasksAdded>(initRequest, errorFactory)

fun BehaviourContext.waitChannelDirectMessagesConfigurationChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<DirectMessagesConfigurationChanged>(initRequest, errorFactory)

fun BehaviourContext.waitSuggestedPostApprovalFailed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuggestedPostApprovalFailed>(initRequest, errorFactory)

fun BehaviourContext.waitSuggestedPostRefunded(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuggestedPostRefunded>(initRequest, errorFactory)

fun BehaviourContext.waitSuggestedPostDeclined(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuggestedPostDeclined>(initRequest, errorFactory)

fun BehaviourContext.waitSuggestedPostPaid(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuggestedPostPaid>(initRequest, errorFactory)

fun BehaviourContext.waitSuggestedPostApproved(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEvents<SuggestedPostApproved>(initRequest, errorFactory)
