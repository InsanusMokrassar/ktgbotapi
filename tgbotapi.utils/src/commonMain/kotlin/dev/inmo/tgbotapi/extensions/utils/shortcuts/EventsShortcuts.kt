@file:Suppress("NOTHING_TO_INLINE", "unused", "EXPERIMENTAL_API_USAGE")

package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.micro_utils.coroutines.plus
import dev.inmo.tgbotapi.types.message.ChannelEventMessage
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.PrivateEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.payments.RefundedPaymentEvent
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.events(): Flow<ChatEventMessage<*>> {
    return channelPostsFlow.mapNotNull { it.data as? ChatEventMessage<*> } + messagesFlow.mapNotNull { it.data as? ChatEventMessage<*> }
}

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.channelEvents(): Flow<ChannelEventMessage<*>> = channelPostsFlow.mapNotNull {
    it.data as? ChannelEventMessage<*>
}

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.groupEvents(): Flow<GroupEventMessage<*>> = messagesFlow.mapNotNull {
    it.data as? GroupEventMessage<*>
}

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.supergroupEvents(): Flow<SupergroupEventMessage<*>> = messagesFlow.mapNotNull {
    it.data as? SupergroupEventMessage<*>
}

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.privateEvents(): Flow<PrivateEventMessage<*>> = messagesFlow.mapNotNull {
    it.data as? PrivateEventMessage<*>
}

@RiskFeature("Use with caution")
inline fun <reified T : ChatEvent, reified O : ChatEventMessage<T>> Flow<ChatEventMessage<*>>.filterByChatEvent(): Flow<O> = mapNotNull {
    if (it.chatEvent is T) it as? O else null
}

@RiskFeature("Use with caution")
inline fun <reified T : ChannelEvent> Flow<ChatEventMessage<*>>.filterChannelEvents() = filterByChatEvent<T, ChannelEventMessage<T>>()

@RiskFeature("Use with caution")
inline fun <reified T : ChannelEvent> FlowsUpdatesFilter.filterChannelEvents() = channelEvents().filterChannelEvents<T>()

inline fun Flow<ChatEventMessage<*>>.channelCreatedEvents() = filterChannelEvents<ChannelChatCreated>()

inline fun FlowsUpdatesFilter.channelCreatedEvents() = filterChannelEvents<ChannelChatCreated>()

inline fun Flow<ChatEventMessage<*>>.deletedChannelPhotoEvents() = filterChannelEvents<DeleteChatPhoto>()

inline fun FlowsUpdatesFilter.deletedChannelPhotoEvents() = filterChannelEvents<DeleteChatPhoto>()

inline fun Flow<ChatEventMessage<*>>.newChannelPhotoEvents() = filterChannelEvents<NewChatPhoto>()

inline fun FlowsUpdatesFilter.newChannelPhotoEvents() = filterChannelEvents<NewChatPhoto>()

inline fun Flow<ChatEventMessage<*>>.newChannelTitleEvents() = filterChannelEvents<NewChatTitle>()

inline fun FlowsUpdatesFilter.newChannelTitleEvents() = filterChannelEvents<NewChatTitle>()

inline fun Flow<ChatEventMessage<*>>.newChannelPinnedMessageEvents() = filterChannelEvents<PinnedMessage>()

inline fun FlowsUpdatesFilter.newChannelPinnedMessageEvents() = filterChannelEvents<PinnedMessage>()

inline fun Flow<ChatEventMessage<*>>.successfulPaymentInChannelEvents() = filterChannelEvents<SuccessfulPaymentEvent>()

inline fun FlowsUpdatesFilter.successfulPaymentInChannelEvents() = filterChannelEvents<SuccessfulPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.refundedPaymentInChannelEvents() = filterChannelEvents<RefundedPaymentEvent>()

inline fun FlowsUpdatesFilter.refundedPaymentInChannelEvents() = filterChannelEvents<RefundedPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.channelEvents() = filterChannelEvents<ChannelEvent>()

@RiskFeature("Use with caution")
inline fun <reified T : GroupEvent> Flow<ChatEventMessage<*>>.filterGroupEvents() = filterByChatEvent<T, GroupEventMessage<T>>()

@RiskFeature("Use with caution")
inline fun <reified T : GroupEvent> FlowsUpdatesFilter.filterGroupEvents() = groupEvents().filterByChatEvent<T, GroupEventMessage<T>>()

inline fun Flow<ChatEventMessage<*>>.groupCreatedEvents() = filterGroupEvents<GroupChatCreated>()

inline fun FlowsUpdatesFilter.groupCreatedEvents() = filterGroupEvents<GroupChatCreated>()

inline fun Flow<ChatEventMessage<*>>.deletedGroupPhotoEvents() = filterGroupEvents<DeleteChatPhoto>()

inline fun FlowsUpdatesFilter.deletedGroupPhotoEvents() = filterGroupEvents<DeleteChatPhoto>()

inline fun Flow<ChatEventMessage<*>>.newGroupMembersEvents() = filterGroupEvents<NewChatMembers>()

inline fun FlowsUpdatesFilter.newGroupMembersEvents() = filterGroupEvents<NewChatMembers>()

inline fun Flow<ChatEventMessage<*>>.leftGroupMemberEvents() = filterGroupEvents<LeftChatMemberEvent>()

inline fun FlowsUpdatesFilter.leftGroupMemberEvents() = filterGroupEvents<LeftChatMemberEvent>()

inline fun Flow<ChatEventMessage<*>>.newGroupPhotoEvents() = filterGroupEvents<NewChatPhoto>()

inline fun FlowsUpdatesFilter.newGroupPhotoEvents() = filterGroupEvents<NewChatPhoto>()

inline fun Flow<ChatEventMessage<*>>.newGroupTitleEvents() = filterGroupEvents<NewChatTitle>()

inline fun FlowsUpdatesFilter.newGroupTitleEvents() = filterGroupEvents<NewChatTitle>()

inline fun Flow<ChatEventMessage<*>>.newGroupPinnedMessageEvents() = filterGroupEvents<PinnedMessage>()

inline fun FlowsUpdatesFilter.newGroupPinnedMessageEvents() = filterGroupEvents<PinnedMessage>()

inline fun Flow<ChatEventMessage<*>>.proximityAlertTriggeredInGroupEvents() = filterGroupEvents<ProximityAlertTriggered>()

inline fun FlowsUpdatesFilter.proximityAlertTriggeredInGroupEvents() = filterGroupEvents<ProximityAlertTriggered>()

inline fun Flow<ChatEventMessage<*>>.successfulPaymentInGroupEvents() = filterGroupEvents<SuccessfulPaymentEvent>()

inline fun FlowsUpdatesFilter.successfulPaymentInGroupEvents() = filterGroupEvents<SuccessfulPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.refundedPaymentInGroupEvents() = filterGroupEvents<RefundedPaymentEvent>()

inline fun FlowsUpdatesFilter.refundedPaymentInGroupEvents() = filterGroupEvents<RefundedPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.groupEvents() = filterGroupEvents<GroupEvent>()

@RiskFeature("Use with caution")
inline fun <reified T : SupergroupEvent> Flow<ChatEventMessage<*>>.filterSupergroupEvents() = filterByChatEvent<T, SupergroupEventMessage<T>>()

@RiskFeature("Use with caution")
inline fun <reified T : SupergroupEvent> FlowsUpdatesFilter.filterSupergroupEvents() = supergroupEvents().filterByChatEvent<T, SupergroupEventMessage<T>>()

inline fun Flow<ChatEventMessage<*>>.supergroupCreatedEvents() = filterSupergroupEvents<SupergroupChatCreated>()

inline fun FlowsUpdatesFilter.supergroupCreatedEvents() = filterSupergroupEvents<SupergroupChatCreated>()

inline fun Flow<ChatEventMessage<*>>.deletedSupergroupPhotoEvents() = filterSupergroupEvents<DeleteChatPhoto>()

inline fun FlowsUpdatesFilter.deletedSupergroupPhotoEvents() = filterSupergroupEvents<DeleteChatPhoto>()

inline fun Flow<ChatEventMessage<*>>.newSupergroupMembersEvents() = filterSupergroupEvents<NewChatMembers>()

inline fun FlowsUpdatesFilter.newSupergroupMembersEvents() = filterSupergroupEvents<NewChatMembers>()

inline fun Flow<ChatEventMessage<*>>.leftSupergroupMemberEvents() = filterSupergroupEvents<LeftChatMemberEvent>()

inline fun FlowsUpdatesFilter.leftSupergroupMemberEvents() = filterSupergroupEvents<LeftChatMemberEvent>()

inline fun Flow<ChatEventMessage<*>>.newSupergroupPhotoEvents() = filterSupergroupEvents<NewChatPhoto>()

inline fun FlowsUpdatesFilter.newSupergroupPhotoEvents() = filterSupergroupEvents<NewChatPhoto>()

inline fun Flow<ChatEventMessage<*>>.newSupergroupTitleEvents() = filterSupergroupEvents<NewChatTitle>()

inline fun FlowsUpdatesFilter.newSupergroupTitleEvents() = filterSupergroupEvents<NewChatTitle>()

inline fun Flow<ChatEventMessage<*>>.newSupergroupPinnedMessageEvents() = filterSupergroupEvents<PinnedMessage>()

inline fun FlowsUpdatesFilter.newSupergroupPinnedMessageEvents() = filterSupergroupEvents<PinnedMessage>()

inline fun Flow<ChatEventMessage<*>>.proximityAlertTriggeredInSupergroupEvents() = filterSupergroupEvents<ProximityAlertTriggered>()

inline fun FlowsUpdatesFilter.proximityAlertTriggeredInSupergroupEvents() = filterSupergroupEvents<ProximityAlertTriggered>()

inline fun Flow<ChatEventMessage<*>>.successfulPaymentInSupergroupEvents() = filterSupergroupEvents<SuccessfulPaymentEvent>()

inline fun FlowsUpdatesFilter.successfulPaymentInSupergroupEvents() = filterSupergroupEvents<SuccessfulPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.refundedPaymentInSupergroupEvents() = filterSupergroupEvents<RefundedPaymentEvent>()

inline fun FlowsUpdatesFilter.refundedPaymentInSupergroupEvents() = filterSupergroupEvents<RefundedPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.supergroupEvents() = filterSupergroupEvents<SupergroupEvent>()

@RiskFeature("Use with caution")
inline fun <reified T : PrivateEvent> Flow<ChatEventMessage<*>>.filterPrivateEvents() = filterByChatEvent<T, PrivateEventMessage<T>>()

@RiskFeature("Use with caution")
inline fun <reified T : PrivateEvent> FlowsUpdatesFilter.filterPrivateEvents() = privateEvents().filterByChatEvent<T, PrivateEventMessage<T>>()

inline fun Flow<ChatEventMessage<*>>.successfulPaymentInPrivateEvents() = filterPrivateEvents<SuccessfulPaymentEvent>()

inline fun FlowsUpdatesFilter.successfulPaymentInPrivateEvents() = filterPrivateEvents<SuccessfulPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.refundedPaymentInPrivateEvents() = filterPrivateEvents<RefundedPaymentEvent>()

inline fun FlowsUpdatesFilter.refundedPaymentInPrivateEvents() = filterPrivateEvents<RefundedPaymentEvent>()

inline fun Flow<ChatEventMessage<*>>.newPrivatePinnedMessageEvents() = filterPrivateEvents<PinnedMessage>()

inline fun FlowsUpdatesFilter.newPrivatePinnedMessageEvents() = filterPrivateEvents<PinnedMessage>()

inline fun Flow<ChatEventMessage<*>>.privateEvents() = filterPrivateEvents<PrivateEvent>()
