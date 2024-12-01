@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatIdChatMessageReactionUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByIdPollMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.chatMessageReactionUpdatedUpdateOrNull
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionUpdated
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <BC : BehaviourContext, reified T : ChatMessageReactionUpdated> BC.onChatMessageReactionUpdated(
    initialFilter: SimpleFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, T, Update>? = null,
    markerFactory: MarkerFactory<in T, Any>? = ByChatIdChatMessageReactionUpdatedMarkerFactory,
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, T>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, T>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.chatMessageReactionUpdatedUpdateOrNull() ?.data as? T) ?.let(::listOfNotNull)
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
suspend fun <BC : BehaviourContext> BC.onChatMessageReactionUpdatedByUser(
    initialFilter: SimpleFilter<ChatMessageReactionUpdated.ByUser>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMessageReactionUpdated.ByUser, Update>? = null,
    markerFactory: MarkerFactory<in ChatMessageReactionUpdated.ByUser, Any>? = ByChatIdChatMessageReactionUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMessageReactionUpdated.ByUser>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMessageReactionUpdated.ByUser>
) = onChatMessageReactionUpdated(
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
suspend fun <BC : BehaviourContext> BC.onChatMessageReactionUpdatedByChat(
    initialFilter: SimpleFilter<ChatMessageReactionUpdated.ByChat>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMessageReactionUpdated.ByChat, Update>? = null,
    markerFactory: MarkerFactory<in ChatMessageReactionUpdated.ByChat, Any>? = ByChatIdChatMessageReactionUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMessageReactionUpdated.ByChat>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMessageReactionUpdated.ByChat>
) = onChatMessageReactionUpdated(
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
suspend fun <BC : BehaviourContext> BC.onChatMessageReactionUpdatedUnknown(
    initialFilter: SimpleFilter<ChatMessageReactionUpdated.Unknown>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatMessageReactionUpdated.Unknown, Update>? = null,
    markerFactory: MarkerFactory<in ChatMessageReactionUpdated.Unknown, Any>? = ByChatIdChatMessageReactionUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatMessageReactionUpdated.Unknown>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatMessageReactionUpdated.Unknown>
) = onChatMessageReactionUpdated(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)
