@file:Suppress("NOTHING_TO_INLINE", "unused", "EXPERIMENTAL_API_USAGE")

package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.micro_utils.coroutines.plus
import dev.inmo.tgbotapi.types.message.ChannelEventMessage
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.events(): Flow<ChatEventMessage<*>> {
    return channelPostFlow.mapNotNull { it.data as? ChatEventMessage<*> } + messageFlow.mapNotNull { it.data as? ChatEventMessage<*> }
}

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.channelEvents(): Flow<ChannelEventMessage<*>> = channelPostFlow.mapNotNull {
    it.data as? ChannelEventMessage<*>
}

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.groupEvents(): Flow<GroupEventMessage<*>> = messageFlow.mapNotNull {
    it.data as? GroupEventMessage<*>
}

@RiskFeature("Use with caution")
inline fun FlowsUpdatesFilter.supergroupEvents(): Flow<SupergroupEventMessage<*>> = messageFlow.mapNotNull {
    it.data as? SupergroupEventMessage<*>
}

@RiskFeature("Use with caution")
inline fun <reified T: ChatEvent, reified O: ChatEventMessage<T>> Flow<ChatEventMessage<*>>.filterByChatEvent(): Flow<O> = mapNotNull {
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
inline fun Flow<ChatEventMessage<*>>.leftGroupMemberEvents() = filterGroupEvents<LeftChatMember>()
inline fun FlowsUpdatesFilter.leftGroupMemberEvents() = filterGroupEvents<LeftChatMember>()
inline fun Flow<ChatEventMessage<*>>.newGroupPhotoEvents() = filterGroupEvents<NewChatPhoto>()
inline fun FlowsUpdatesFilter.newGroupPhotoEvents() = filterGroupEvents<NewChatPhoto>()
inline fun Flow<ChatEventMessage<*>>.newGroupTitleEvents() = filterGroupEvents<NewChatTitle>()
inline fun FlowsUpdatesFilter.newGroupTitleEvents() = filterGroupEvents<NewChatTitle>()
inline fun Flow<ChatEventMessage<*>>.newGroupPinnedMessageEvents() = filterGroupEvents<PinnedMessage>()
inline fun FlowsUpdatesFilter.newGroupPinnedMessageEvents() = filterGroupEvents<PinnedMessage>()
inline fun Flow<ChatEventMessage<*>>.proximityAlertTriggeredInGroupEvents() = filterGroupEvents<ProximityAlertTriggered>()
inline fun FlowsUpdatesFilter.proximityAlertTriggeredInGroupEvents() = filterGroupEvents<ProximityAlertTriggered>()
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
inline fun Flow<ChatEventMessage<*>>.leftSupergroupMemberEvents() = filterSupergroupEvents<LeftChatMember>()
inline fun FlowsUpdatesFilter.leftSupergroupMemberEvents() = filterSupergroupEvents<LeftChatMember>()
inline fun Flow<ChatEventMessage<*>>.newSupergroupPhotoEvents() = filterSupergroupEvents<NewChatPhoto>()
inline fun FlowsUpdatesFilter.newSupergroupPhotoEvents() = filterSupergroupEvents<NewChatPhoto>()
inline fun Flow<ChatEventMessage<*>>.newSupergroupTitleEvents() = filterSupergroupEvents<NewChatTitle>()
inline fun FlowsUpdatesFilter.newSupergroupTitleEvents() = filterSupergroupEvents<NewChatTitle>()
inline fun Flow<ChatEventMessage<*>>.newSupergroupPinnedMessageEvents() = filterSupergroupEvents<PinnedMessage>()
inline fun FlowsUpdatesFilter.newSupergroupPinnedMessageEvents() = filterSupergroupEvents<PinnedMessage>()
inline fun Flow<ChatEventMessage<*>>.proximityAlertTriggeredInSupergroupEvents() = filterSupergroupEvents<ProximityAlertTriggered>()
inline fun FlowsUpdatesFilter.proximityAlertTriggeredInSupergroupEvents() = filterSupergroupEvents<ProximityAlertTriggered>()
inline fun Flow<ChatEventMessage<*>>.supergroupEvents() = filterSupergroupEvents<SupergroupEvent>()
