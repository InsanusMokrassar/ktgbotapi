@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asBaseSentMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asChatEventMessage
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified T : ChatEvent> BehaviourContext.onEvent(
    noinline initialFilter: SimpleFilter<ChatEventMessage<T>>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<T>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<T>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<T>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    (it.asBaseSentMessageUpdate() ?.data ?.asChatEventMessage() ?.takeIf { it.chatEvent is T } as? ChatEventMessage<T>) ?.let(::listOfNotNull)
}

@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onChannelEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<ChannelEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelEvent>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<ChatEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChatEvent>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onVoiceChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEvent>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onVoiceChatStartedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatStarted>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatStarted>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatStarted>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onVoiceChatEndedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatEnded>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatEnded>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEnded>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onVoiceChatParticipantsInvitedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatParticipantsInvited>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatParticipantsInvited>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatParticipantsInvited>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onMessageAutoDeleteTimerChangedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<MessageAutoDeleteTimerChanged>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<MessageAutoDeleteTimerChanged>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<MessageAutoDeleteTimerChanged>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onCommonEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<CommonEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<CommonEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<CommonEvent>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onGroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<GroupEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupEvent>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onSupergroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<SupergroupEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupEvent>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)

@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onChannelChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<ChannelChatCreated>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelChatCreated>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onDeleteChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<DeleteChatPhoto>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<DeleteChatPhoto>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<DeleteChatPhoto>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onGroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<GroupChatCreated>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupChatCreated>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onLeftChatMember(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<LeftChatMember>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<LeftChatMember>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<LeftChatMember>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onNewChatMembers(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<NewChatMembers>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatMembers>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatMembers>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onNewChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<NewChatPhoto>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatPhoto>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatPhoto>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onNewChatTitle(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<NewChatTitle>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatTitle>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatTitle>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onPinnedMessage(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<PinnedMessage>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<PinnedMessage>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<PinnedMessage>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onProximityAlertTriggered(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<ProximityAlertTriggered>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ProximityAlertTriggered>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ProximityAlertTriggered>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)
@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onSupergroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatEventMessage<SupergroupChatCreated>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupChatCreated>>
) = onEvent(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null, markerFactory, scenarioReceiver)



/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onChannelEvent(
    initialFilter: SimpleFilter<ChatEventMessage<ChannelEvent>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<ChannelEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onChatEvent(
    initialFilter: SimpleFilter<ChatEventMessage<ChatEvent>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<ChatEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChatEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVoiceChatEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VoiceChatEvent>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<VoiceChatEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVoiceChatStartedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VoiceChatStarted>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<VoiceChatStarted>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatStarted>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatStarted>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVoiceChatEndedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VoiceChatEnded>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<VoiceChatEnded>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatEnded>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEnded>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVoiceChatParticipantsInvitedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VoiceChatParticipantsInvited>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<VoiceChatParticipantsInvited>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatParticipantsInvited>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatParticipantsInvited>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onMessageAutoDeleteTimerChangedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<MessageAutoDeleteTimerChanged>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<MessageAutoDeleteTimerChanged>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<MessageAutoDeleteTimerChanged>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<MessageAutoDeleteTimerChanged>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onCommonEvent(
    initialFilter: SimpleFilter<ChatEventMessage<CommonEvent>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<CommonEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<CommonEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<CommonEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onGroupEvent(
    initialFilter: SimpleFilter<ChatEventMessage<GroupEvent>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<GroupEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onSupergroupEvent(
    initialFilter: SimpleFilter<ChatEventMessage<SupergroupEvent>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<SupergroupEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onChannelChatCreated(
    initialFilter: SimpleFilter<ChatEventMessage<ChannelChatCreated>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<ChannelChatCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelChatCreated>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onDeleteChatPhoto(
    initialFilter: SimpleFilter<ChatEventMessage<DeleteChatPhoto>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<DeleteChatPhoto>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<DeleteChatPhoto>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<DeleteChatPhoto>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onGroupChatCreated(
    initialFilter: SimpleFilter<ChatEventMessage<GroupChatCreated>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<GroupChatCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupChatCreated>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onLeftChatMember(
    initialFilter: SimpleFilter<ChatEventMessage<LeftChatMember>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<LeftChatMember>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<LeftChatMember>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<LeftChatMember>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onNewChatMembers(
    initialFilter: SimpleFilter<ChatEventMessage<NewChatMembers>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<NewChatMembers>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatMembers>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatMembers>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onNewChatPhoto(
    initialFilter: SimpleFilter<ChatEventMessage<NewChatPhoto>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<NewChatPhoto>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatPhoto>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatPhoto>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onNewChatTitle(
    initialFilter: SimpleFilter<ChatEventMessage<NewChatTitle>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<NewChatTitle>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatTitle>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatTitle>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onPinnedMessage(
    initialFilter: SimpleFilter<ChatEventMessage<PinnedMessage>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<PinnedMessage>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<PinnedMessage>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<PinnedMessage>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onProximityAlertTriggered(
    initialFilter: SimpleFilter<ChatEventMessage<ProximityAlertTriggered>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<ProximityAlertTriggered>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ProximityAlertTriggered>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ProximityAlertTriggered>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onSupergroupChatCreated(
    initialFilter: SimpleFilter<ChatEventMessage<SupergroupChatCreated>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatEventMessage<SupergroupChatCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupChatCreated>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
