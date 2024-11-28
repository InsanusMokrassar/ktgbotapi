package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserBusinessConnectionUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.businessConnectionUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.disabledOrNull
import dev.inmo.tgbotapi.extensions.utils.enabledOrNull
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
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
suspend fun <BC : BehaviourContext> BC.onBusinessConnection(
    initialFilter: SimpleFilter<BusinessConnection>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, BusinessConnection, Update>? = null,
    markerFactory: MarkerFactory<in BusinessConnection, Any>? = ByUserBusinessConnectionUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, BusinessConnection>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, BusinessConnection>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.businessConnectionUpdateOrNull() ?.data) ?.let(::listOfNotNull)
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
suspend fun <BC : BehaviourContext> BC.onBusinessConnectionEnabled(
    initialFilter: SimpleFilter<BusinessConnection.Enabled>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, BusinessConnection.Enabled, Update>? = null,
    markerFactory: MarkerFactory<in BusinessConnection.Enabled, Any>? = ByUserBusinessConnectionUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, BusinessConnection.Enabled>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, BusinessConnection.Enabled>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.businessConnectionUpdateOrNull() ?.data ?.enabledOrNull()) ?.let(::listOfNotNull)
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
suspend fun <BC : BehaviourContext> BC.onBusinessConnectionDisabled(
    initialFilter: SimpleFilter<BusinessConnection.Disabled>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, BusinessConnection.Disabled, Update>? = null,
    markerFactory: MarkerFactory<in BusinessConnection.Disabled, Any>? = ByUserBusinessConnectionUpdatedMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, BusinessConnection.Disabled>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, BusinessConnection.Disabled>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.businessConnectionUpdateOrNull() ?.data ?.disabledOrNull()) ?.let(::listOfNotNull)
}

