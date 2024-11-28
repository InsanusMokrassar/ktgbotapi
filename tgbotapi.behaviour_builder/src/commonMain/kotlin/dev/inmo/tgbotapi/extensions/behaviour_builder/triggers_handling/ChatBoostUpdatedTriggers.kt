@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByIdChatBoostUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.chatBoostUpdatedUpdateOrNull
import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated
import dev.inmo.tgbotapi.types.update.abstracts.Update

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
suspend fun <BC : BehaviourContext> BC.onChatBoostUpdated(
    initialFilter: SimpleFilter<ChatBoostUpdated>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatBoostUpdated, Update>? = null,
    markerFactory: MarkerFactory<ChatBoostUpdated, Any>? = ByIdChatBoostUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatBoostUpdated>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatBoostUpdated>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.chatBoostUpdatedUpdateOrNull() ?.data) ?.let(::listOfNotNull)
}
