package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.guestMessageUpdateOrNull
import dev.inmo.tgbotapi.types.message.abstracts.RequestGuestContentMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver].
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that data
 */
fun <BC : BehaviourContext> BC.onGuestMessage(
    initialFilter: SimpleFilter<RequestGuestContentMessage<*>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, RequestGuestContentMessage<*>, Update>? = null,
    markerFactory: MarkerFactory<in RequestGuestContentMessage<*>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, RequestGuestContentMessage<*>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, RequestGuestContentMessage<*>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.guestMessageUpdateOrNull() ?.data) ?.let(::listOfNotNull)
}
