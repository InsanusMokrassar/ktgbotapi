package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.ShippingQueryFilterByUser
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserShippingQueryMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.shippingQueryUpdateOrNull
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Please, remember that you must answer to this type of queries using something like
 * [dev.inmo.tgbotapi.extensions.api.answers.payments.answerShippingQueryOk] or
 * [dev.inmo.tgbotapi.extensions.api.answers.payments.answerShippingQueryError]
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
suspend fun <BC : BehaviourContext> BC.onShippingQuery(
    initialFilter: SimpleFilter<ShippingQuery>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ShippingQuery, Update>? = ShippingQueryFilterByUser,
    markerFactory: MarkerFactory<in ShippingQuery, Any>? = ByUserShippingQueryMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ShippingQuery>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ShippingQuery>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.shippingQueryUpdateOrNull() ?.data) ?.let(::listOfNotNull)
}
