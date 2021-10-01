@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.ChatMemberUpdatedFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatChatMemberUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.types.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified U : ChatMemberUpdatedUpdate> BehaviourContext.onChatMemberUpdatedInternal(
    noinline initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    ((it as? U) ?.data) ?.let(::listOfNotNull)
}


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
suspend fun BehaviourContext.onChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<ChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun BehaviourContext.onCommonChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<CommonChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun BehaviourContext.onMyChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<MyChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
