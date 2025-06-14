@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatChatMemberUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal inline fun <BC : BehaviourContext, reified U : ChatMemberUpdatedUpdate> BC.onChatMemberUpdatedInternal(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    ((it as? U) ?.data) ?.let(::listOfNotNull)
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
fun <BC : BehaviourContext> BC.onChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onCommonChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onMyChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberJoined(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberJoinedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberLeft(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberLeftFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberSubscribed(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberSubscribedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberSubscriptionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberSubscriptionChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberUnsubscribed(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberUnsubscribedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberGotPromoted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberGotPromotedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberGotPromotionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberGotPromotionChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberGotDemoted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberGotDemotedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberBecameOwner(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberBecameOwnerFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberCeasedOwnership(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberCeasedOwnershipFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberGotRestricted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberGotRestrictedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberGotRestrictionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberGotRestrictionsChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
fun <BC : BehaviourContext> BC.onChatMemberGotUnrestricted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberGotUnrestrictedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onChatMemberKicked(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, ChatMemberUpdatedUpdate>(
    chatMemberKickedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberJoined(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberJoinedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberLeft(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberLeftFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberSubscribed(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberSubscribedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberSubscriptionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberSubscriptionChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberUnsubscribed(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberUnsubscribedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberGotPromoted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberGotPromotedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberGotPromotionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberGotPromotionChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberGotDemoted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberGotDemotedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberBecameOwner(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberBecameOwnerFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberCeasedOwnership(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberCeasedOwnershipFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberGotRestricted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberGotRestrictedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberGotRestrictionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberGotRestrictionsChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberGotUnrestricted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberGotUnrestrictedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onCommonChatMemberKicked(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, CommonChatMemberUpdatedUpdate>(
    chatMemberKickedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberJoined(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberJoinedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberLeft(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberLeftFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberSubscribed(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberSubscribedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberSubscriptionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberSubscriptionChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberUnsubscribed(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberUnsubscribedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberGotPromoted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberGotPromotedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberGotPromotionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberGotPromotionChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberGotDemoted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberGotDemotedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberBecameOwner(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberBecameOwnerFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberCeasedOwnership(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberCeasedOwnershipFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberGotRestricted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberGotRestrictedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberGotRestrictionChanged(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberGotRestrictionsChangedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberGotUnrestricted(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberGotUnrestrictedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMyChatMemberKicked(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any>? = ByChatChatMemberUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMemberUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<BC, MyChatMemberUpdatedUpdate>(
    chatMemberKickedFilter * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)
