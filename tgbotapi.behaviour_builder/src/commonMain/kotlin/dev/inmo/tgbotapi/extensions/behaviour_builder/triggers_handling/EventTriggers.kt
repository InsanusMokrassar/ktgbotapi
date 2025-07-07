@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.baseSentMessageUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.chatEventMessageOrNull
import dev.inmo.tgbotapi.types.ChannelDirectMessagesConfigurationChanged
import dev.inmo.tgbotapi.types.PaidMessagePriceChanged
import dev.inmo.tgbotapi.types.chat.ChatBackground
import dev.inmo.tgbotapi.types.checklists.ChecklistTasksAdded
import dev.inmo.tgbotapi.types.checklists.ChecklistTasksDone
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived
import dev.inmo.tgbotapi.types.giveaway.GiveawayCreated
import dev.inmo.tgbotapi.types.giveaway.GiveawayPrivateResults
import dev.inmo.tgbotapi.types.message.ChannelEventMessage
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
import dev.inmo.tgbotapi.types.message.PrivateEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage
import dev.inmo.tgbotapi.types.message.payments.RefundedPaymentEvent
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import dev.inmo.tgbotapi.types.request.ChatShared
import dev.inmo.tgbotapi.types.request.ChatSharedRequest
import dev.inmo.tgbotapi.types.request.UsersShared
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal inline fun <BC : BehaviourContext, reified T : ChatEvent> BC.onEvent(
    initialFilter: SimpleFilter<ChatEventMessage<T>>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<T>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<T>, Any>? = ByChatMessageMarkerFactory,
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<T>>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<T>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    @Suppress("UNCHECKED_CAST")
    (it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.takeIf { it.chatEvent is T } as? ChatEventMessage<T>) ?.let(::listOfNotNull)
}

internal inline fun <BC : BehaviourContext, reified T : ChatEvent, reified CEM : ChatEventMessage<T>> BC.onEventWithCustomChatEventMessage(
    initialFilter: SimpleFilter<CEM>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CEM, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in CEM, Any>? = ByChatMessageMarkerFactory,
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, CEM>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CEM>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    @Suppress("UNCHECKED_CAST")
    (it.baseSentMessageUpdateOrNull() ?.data ?.chatEventMessageOrNull() ?.takeIf { it.chatEvent is T } as? CEM) ?.let(::listOfNotNull)
}

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChannelEvent(
    initialFilter: SimpleFilter<ChatEventMessage<ChannelEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ChannelEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChannelEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ChannelEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onPrivateEvent(
    initialFilter: SimpleFilter<ChatEventMessage<PrivateEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<PrivateEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<PrivateEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<PrivateEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<PrivateEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChatEvent(
    initialFilter: SimpleFilter<ChatEventMessage<ChatEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ChatEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChatEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ChatEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onVideoChatEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VideoChatEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<VideoChatEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VideoChatEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<VideoChatEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<VideoChatEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onVideoChatStartedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VideoChatStarted>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<VideoChatStarted>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VideoChatStarted>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<VideoChatStarted>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<VideoChatStarted>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onVideoChatEndedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VideoChatEnded>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<VideoChatEnded>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VideoChatEnded>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<VideoChatEnded>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<VideoChatEnded>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onVideoChatParticipantsInvitedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<VideoChatParticipantsInvited>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<VideoChatParticipantsInvited>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<VideoChatParticipantsInvited>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<VideoChatParticipantsInvited>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<VideoChatParticipantsInvited>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onMessageAutoDeleteTimerChangedEvent(
    initialFilter: SimpleFilter<ChatEventMessage<MessageAutoDeleteTimerChanged>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<MessageAutoDeleteTimerChanged>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<MessageAutoDeleteTimerChanged>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<MessageAutoDeleteTimerChanged>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<MessageAutoDeleteTimerChanged>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onPublicChatEvent(
    initialFilter: SimpleFilter<ChatEventMessage<PublicChatEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<PublicChatEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<PublicChatEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<PublicChatEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<PublicChatEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onCommonEvent(
    initialFilter: SimpleFilter<ChatEventMessage<CommonEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<CommonEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<CommonEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<CommonEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<CommonEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onGroupEvent(
    initialFilter: SimpleFilter<ChatEventMessage<GroupEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<GroupEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GroupEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<GroupEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onSupergroupEvent(
    initialFilter: SimpleFilter<ChatEventMessage<SupergroupEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<SupergroupEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<SupergroupEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<SupergroupEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChannelChatCreated(
    initialFilter: SimpleFilter<ChatEventMessage<ChannelChatCreated>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ChannelChatCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelChatCreated>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChannelChatCreated>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ChannelChatCreated>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onDeleteChatPhoto(
    initialFilter: SimpleFilter<ChatEventMessage<DeleteChatPhoto>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<DeleteChatPhoto>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<DeleteChatPhoto>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<DeleteChatPhoto>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<DeleteChatPhoto>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onGroupChatCreated(
    initialFilter: SimpleFilter<ChatEventMessage<GroupChatCreated>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<GroupChatCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupChatCreated>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GroupChatCreated>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<GroupChatCreated>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onLeftChatMember(
    initialFilter: SimpleFilter<ChatEventMessage<LeftChatMemberEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<LeftChatMemberEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<LeftChatMemberEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<LeftChatMemberEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<LeftChatMemberEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onNewChatMembers(
    initialFilter: SimpleFilter<ChatEventMessage<NewChatMembers>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<NewChatMembers>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatMembers>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<NewChatMembers>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<NewChatMembers>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onNewChatPhoto(
    initialFilter: SimpleFilter<ChatEventMessage<NewChatPhoto>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<NewChatPhoto>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatPhoto>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<NewChatPhoto>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<NewChatPhoto>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onNewChatTitle(
    initialFilter: SimpleFilter<ChatEventMessage<NewChatTitle>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<NewChatTitle>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatTitle>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<NewChatTitle>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<NewChatTitle>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onPinnedMessage(
    initialFilter: SimpleFilter<ChatEventMessage<PinnedMessage>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<PinnedMessage>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<PinnedMessage>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<PinnedMessage>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<PinnedMessage>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onProximityAlertTriggered(
    initialFilter: SimpleFilter<ChatEventMessage<ProximityAlertTriggered>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ProximityAlertTriggered>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ProximityAlertTriggered>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ProximityAlertTriggered>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ProximityAlertTriggered>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onSupergroupChatCreated(
    initialFilter: SimpleFilter<ChatEventMessage<SupergroupChatCreated>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<SupergroupChatCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupChatCreated>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<SupergroupChatCreated>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<SupergroupChatCreated>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * Please, remember that [SuccessfulPaymentEvent] will be retrieved only in case you will correctly handle
 * [dev.inmo.tgbotapi.types.payments.PreCheckoutQuery] (via [onPreCheckoutQuery], for example)
 *
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onSuccessfulPayment(
    initialFilter: SimpleFilter<ChatEventMessage<SuccessfulPaymentEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<SuccessfulPaymentEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<SuccessfulPaymentEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<SuccessfulPaymentEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<SuccessfulPaymentEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onRefundedPayment(
    initialFilter: SimpleFilter<ChatEventMessage<RefundedPaymentEvent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<RefundedPaymentEvent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<RefundedPaymentEvent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<RefundedPaymentEvent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<RefundedPaymentEvent>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onUserLoggedIn(
    initialFilter: SimpleFilter<ChatEventMessage<UserLoggedIn>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<UserLoggedIn>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<UserLoggedIn>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<UserLoggedIn>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<UserLoggedIn>>
) = onEvent(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onWebAppData(
    initialFilter: SimpleFilter<PrivateEventMessage<WebAppData>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PrivateEventMessage<WebAppData>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<WebAppData>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<WebAppData>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PrivateEventMessage<WebAppData>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onForumTopicClosed(
    initialFilter: SimpleFilter<SupergroupEventMessage<ForumTopicClosed>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, SupergroupEventMessage<ForumTopicClosed>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ForumTopicClosed>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ForumTopicClosed>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, SupergroupEventMessage<ForumTopicClosed>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onForumTopicCreated(
    initialFilter: SimpleFilter<SupergroupEventMessage<ForumTopicCreated>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, SupergroupEventMessage<ForumTopicCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ForumTopicCreated>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ForumTopicCreated>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, SupergroupEventMessage<ForumTopicCreated>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onForumTopicReopened(
    initialFilter: SimpleFilter<SupergroupEventMessage<ForumTopicReopened>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, SupergroupEventMessage<ForumTopicReopened>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ForumTopicReopened>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ForumTopicReopened>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, SupergroupEventMessage<ForumTopicReopened>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onForumTopicEdited(
    initialFilter: SimpleFilter<SupergroupEventMessage<ForumTopicEdited>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, SupergroupEventMessage<ForumTopicEdited>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ForumTopicEdited>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ForumTopicEdited>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, SupergroupEventMessage<ForumTopicEdited>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onGeneralForumTopicHidden(
    initialFilter: SimpleFilter<SupergroupEventMessage<GeneralForumTopicHidden>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, SupergroupEventMessage<GeneralForumTopicHidden>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GeneralForumTopicHidden>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GeneralForumTopicHidden>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, SupergroupEventMessage<GeneralForumTopicHidden>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onGeneralForumTopicUnhidden(
    initialFilter: SimpleFilter<SupergroupEventMessage<GeneralForumTopicUnhidden>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, SupergroupEventMessage<GeneralForumTopicUnhidden>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GeneralForumTopicUnhidden>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GeneralForumTopicUnhidden>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, SupergroupEventMessage<GeneralForumTopicUnhidden>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onWriteAccessAllowed(
    initialFilter: SimpleFilter<ChatEventMessage<WriteAccessAllowed>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<WriteAccessAllowed>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<WriteAccessAllowed>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<WriteAccessAllowed>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<WriteAccessAllowed>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onWriteAccessAllowedFromRequest(
    initialFilter: SimpleFilter<ChatEventMessage<WriteAccessAllowed.FromRequest>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<WriteAccessAllowed.FromRequest>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<WriteAccessAllowed.FromRequest>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<WriteAccessAllowed.FromRequest>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<WriteAccessAllowed.FromRequest>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onWriteAccessAllowedFromAttachmentMenu(
    initialFilter: SimpleFilter<ChatEventMessage<WriteAccessAllowed.FromAttachmentMenu>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<WriteAccessAllowed.FromAttachmentMenu>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<WriteAccessAllowed.FromAttachmentMenu>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<WriteAccessAllowed.FromAttachmentMenu>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<WriteAccessAllowed.FromAttachmentMenu>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onWriteAccessAllowedOther(
    initialFilter: SimpleFilter<ChatEventMessage<WriteAccessAllowed.Other>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<WriteAccessAllowed.Other>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<WriteAccessAllowed.Other>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<WriteAccessAllowed.Other>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<WriteAccessAllowed.Other>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onWriteAccessAllowedFromWebAppLink(
    initialFilter: SimpleFilter<ChatEventMessage<WriteAccessAllowed.FromWebAppLink>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<WriteAccessAllowed.FromWebAppLink>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<WriteAccessAllowed.FromWebAppLink>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<WriteAccessAllowed.FromWebAppLink>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<WriteAccessAllowed.FromWebAppLink>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChatSharedRequest(
    initialFilter: SimpleFilter<PrivateEventMessage<ChatSharedRequest>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PrivateEventMessage<ChatSharedRequest>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatSharedRequest>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChatSharedRequest>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PrivateEventMessage<ChatSharedRequest>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onUsersShared(
    initialFilter: SimpleFilter<PrivateEventMessage<UsersShared>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PrivateEventMessage<UsersShared>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<UsersShared>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<UsersShared>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PrivateEventMessage<UsersShared>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onUserShared(
    initialFilter: SimpleFilter<PrivateEventMessage<UsersShared>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PrivateEventMessage<UsersShared>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<UsersShared>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<UsersShared>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PrivateEventMessage<UsersShared>>
) = onUsersShared(initialFilter * { it.chatEvent.userIds.size == 1 }, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChatShared(
    initialFilter: SimpleFilter<PrivateEventMessage<ChatShared>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PrivateEventMessage<ChatShared>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatShared>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChatShared>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PrivateEventMessage<ChatShared>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChatBoostAdded(
    initialFilter: SimpleFilter<ChatEventMessage<ChatBoostAdded>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ChatBoostAdded>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatBoostAdded>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChatBoostAdded>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ChatBoostAdded>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChatBackgroundSet(
    initialFilter: SimpleFilter<ChatEventMessage<ChatBackground>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ChatBackground>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatBackground>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChatBackground>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ChatBackground>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onGiveawayCreated(
    initialFilter: SimpleFilter<ChatEventMessage<GiveawayCreated>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<GiveawayCreated>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GiveawayCreated>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GiveawayCreated>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<GiveawayCreated>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onGiveawayCompleted(
    initialFilter: SimpleFilter<ChatEventMessage<GiveawayPrivateResults>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<GiveawayPrivateResults>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GiveawayPrivateResults>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GiveawayPrivateResults>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<GiveawayPrivateResults>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)




/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onGiveawayCompletedWithPrivateWinners(
    initialFilter: SimpleFilter<ChatEventMessage<GiveawayPrivateResults>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<GiveawayPrivateResults>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GiveawayPrivateResults>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GiveawayPrivateResults>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<GiveawayPrivateResults>>
) = onGiveawayCompleted(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onPaidMessagePriceChanged(
    initialFilter: SimpleFilter<ChatEventMessage<PaidMessagePriceChanged>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<PaidMessagePriceChanged>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<PaidMessagePriceChanged>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<PaidMessagePriceChanged>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<PaidMessagePriceChanged>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onRegularGiftSentOrReceived(
    initialFilter: SimpleFilter<ChatEventMessage<GiftSentOrReceived.Regular>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<GiftSentOrReceived.Regular>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GiftSentOrReceived.Regular>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GiftSentOrReceived.Regular>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<GiftSentOrReceived.Regular>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onUniqueGiftSentOrReceived(
    initialFilter: SimpleFilter<ChatEventMessage<GiftSentOrReceived.Unique>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<GiftSentOrReceived.Unique>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<GiftSentOrReceived.Unique>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<GiftSentOrReceived.Unique>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<GiftSentOrReceived.Unique>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChecklistTasksDone(
    initialFilter: SimpleFilter<ChatEventMessage<ChecklistTasksDone>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ChecklistTasksDone>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChecklistTasksDone>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChecklistTasksDone>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ChecklistTasksDone>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChecklistTasksAdded(
    initialFilter: SimpleFilter<ChatEventMessage<ChecklistTasksAdded>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatEventMessage<ChecklistTasksAdded>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChatEventMessage<ChecklistTasksAdded>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatEventMessage<ChecklistTasksAdded>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatEventMessage<ChecklistTasksAdded>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
fun <BC : BehaviourContext> BC.onChannelDirectMessagesConfigurationChanged(
    initialFilter: SimpleFilter<ChannelEventMessage<ChannelDirectMessagesConfigurationChanged>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChannelEventMessage<ChannelDirectMessagesConfigurationChanged>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ChannelEventMessage<ChannelDirectMessagesConfigurationChanged>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChannelEventMessage<ChannelDirectMessagesConfigurationChanged>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChannelEventMessage<ChannelDirectMessagesConfigurationChanged>>
) = onEventWithCustomChatEventMessage(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)
