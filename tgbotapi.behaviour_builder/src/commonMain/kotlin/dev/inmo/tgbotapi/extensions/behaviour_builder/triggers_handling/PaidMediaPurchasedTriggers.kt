package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserBusinessConnectionUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserPaidMediaPurchasedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.businessConnectionUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.disabledOrNull
import dev.inmo.tgbotapi.extensions.utils.enabledOrNull
import dev.inmo.tgbotapi.extensions.utils.paidMediaPurchasedUpdateOrNull
import dev.inmo.tgbotapi.types.PaidMediaPayload
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
import dev.inmo.tgbotapi.types.message.payments.PaidMediaPurchased
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
suspend fun <BC : BehaviourContext> BC.onPaidMediaPurchased(
    initialFilter: SimpleFilter<PaidMediaPurchased>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PaidMediaPurchased, Update>? = null,
    markerFactory: MarkerFactory<in PaidMediaPurchased, Any>? = ByUserPaidMediaPurchasedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, PaidMediaPurchased>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PaidMediaPurchased>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.paidMediaPurchasedUpdateOrNull() ?.data) ?.let(::listOfNotNull)
}

/**
 * @param paidMediaPayloadRegex Will be used to filter incoming [PaidMediaPurchased] updates
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
suspend fun <BC : BehaviourContext> BC.onPaidMediaPurchased(
    paidMediaPayloadRegex: Regex,
    initialFilter: SimpleFilter<PaidMediaPurchased>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PaidMediaPurchased, Update>? = null,
    markerFactory: MarkerFactory<in PaidMediaPurchased, Any>? = ByUserPaidMediaPurchasedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, PaidMediaPurchased>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PaidMediaPurchased>
) = onPaidMediaPurchased(
    SimpleFilter<PaidMediaPurchased> { paidMediaPayloadRegex.matches(it.payload.string) } * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

/**
 * @param paidMediaPayloadRegex Will be used to filter incoming [PaidMediaPurchased] updates
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
suspend fun <BC : BehaviourContext> BC.onPaidMediaPurchased(
    paidMediaPayload: PaidMediaPayload,
    initialFilter: SimpleFilter<PaidMediaPurchased>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PaidMediaPurchased, Update>? = null,
    markerFactory: MarkerFactory<in PaidMediaPurchased, Any>? = ByUserPaidMediaPurchasedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, PaidMediaPurchased>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PaidMediaPurchased>
) = onPaidMediaPurchased(
    SimpleFilter<PaidMediaPurchased> { it.payload == paidMediaPayload } * initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)
