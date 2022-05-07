package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.PreCheckoutQueryFilterByUser
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserPreCheckoutQueryMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asPreCheckoutQueryUpdate
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Please, remember that you must answer to this type of queries using something like
 * [dev.inmo.tgbotapi.extensions.api.answers.payments.answerPreCheckoutQueryOk] or
 * [dev.inmo.tgbotapi.extensions.api.answers.payments.answerPreCheckoutQueryError]
 *
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
suspend fun <BC : BehaviourContext> BC.onPreCheckoutQuery(
    initialFilter: SimpleFilter<PreCheckoutQuery>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PreCheckoutQuery, Update>? = PreCheckoutQueryFilterByUser,
    markerFactory: MarkerFactory<in PreCheckoutQuery, Any> = ByUserPreCheckoutQueryMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PreCheckoutQuery>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    (it.asPreCheckoutQueryUpdate() ?.data) ?.let(::listOfNotNull)
}
