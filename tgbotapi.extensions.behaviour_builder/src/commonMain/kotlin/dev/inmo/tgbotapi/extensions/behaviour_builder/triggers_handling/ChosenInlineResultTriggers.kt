@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.*
import dev.inmo.tgbotapi.extensions.utils.asChosenInlineResultUpdate
import dev.inmo.tgbotapi.extensions.utils.asPollUpdate
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.*
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified T : ChosenInlineResult> BehaviourContext.onChosenInlineResultBase(
    noinline initialFilter: SimpleFilter<T>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>? = null,
    markerFactory: MarkerFactory<in T, Any> = ByUserIdChosenInlineResultMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, T>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    (it.asChosenInlineResultUpdate() ?.data as? T) ?.let(::listOfNotNull)
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
suspend fun BehaviourContext.onChosenInlineResult(
    initialFilter: SimpleFilter<ChosenInlineResult>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChosenInlineResult, Update>? = null,
    markerFactory: MarkerFactory<in ChosenInlineResult, Any> = ByUserIdChosenInlineResultMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChosenInlineResult>
) = onChosenInlineResultBase(
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
suspend fun BehaviourContext.onLocationChosenInlineResult(
    initialFilter: SimpleFilter<LocationChosenInlineResult>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, LocationChosenInlineResult, Update>? = null,
    markerFactory: MarkerFactory<in LocationChosenInlineResult, Any> = ByUserIdChosenInlineResultMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, LocationChosenInlineResult>
) = onChosenInlineResultBase(
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
suspend fun BehaviourContext.onBaseChosenInlineResult(
    initialFilter: SimpleFilter<BaseChosenInlineResult>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, BaseChosenInlineResult, Update>? = null,
    markerFactory: MarkerFactory<in BaseChosenInlineResult, Any> = ByUserIdChosenInlineResultMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, BaseChosenInlineResult>
) = onChosenInlineResultBase(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
