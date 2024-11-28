@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByIdPollMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.pollUpdateOrNull
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <BC : BehaviourContext, reified T : Poll> BC.onPollUpdatedBase(
    initialFilter: SimpleFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, T, Update>? = null,
    markerFactory: MarkerFactory<in T, Any>? = ByIdPollMarkerFactory,
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, T>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, T>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it.pollUpdateOrNull() ?.data as? T) ?.let(::listOfNotNull)
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
suspend fun <BC : BehaviourContext> BC.onPollUpdates(
    initialFilter: SimpleFilter<Poll>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, Poll, Update>? = null,
    markerFactory: MarkerFactory<in Poll, Any>? = ByIdPollMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, Poll>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, Poll>
) = onPollUpdatedBase(
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
suspend fun <BC : BehaviourContext> BC.onRegularPollUpdates(
    initialFilter: SimpleFilter<RegularPoll>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, RegularPoll, Update>? = null,
    markerFactory: MarkerFactory<in RegularPoll, Any>? = ByIdPollMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, RegularPoll>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, RegularPoll>
) = onPollUpdatedBase(
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
suspend fun <BC : BehaviourContext> BC.onQuizPollUpdates(
    initialFilter: SimpleFilter<QuizPoll>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, QuizPoll, Update>? = null,
    markerFactory: MarkerFactory<in QuizPoll, Any>? = ByIdPollMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, QuizPoll>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, QuizPoll>
) = onPollUpdatedBase(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)
