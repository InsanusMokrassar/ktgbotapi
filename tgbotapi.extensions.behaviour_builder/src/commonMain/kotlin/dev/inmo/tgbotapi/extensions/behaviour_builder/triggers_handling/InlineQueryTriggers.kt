package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.InlineQueryFilterByUser
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserInlineQueryMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asInlineQueryUpdate
import dev.inmo.tgbotapi.types.InlineQueries.query.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified T : InlineQuery> BehaviourContext.onInlineQuery(
    noinline initialFilter: SimpleFilter<T>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>? = InlineQueryFilterByUser,
    markerFactory: MarkerFactory<in T, Any> = ByUserInlineQueryMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, T>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    (it.asInlineQueryUpdate() ?.data as? T) ?.let(::listOfNotNull)
}


@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onAnyInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<InlineQuery>? = null,
    markerFactory: MarkerFactory<in InlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineQuery>
) = onInlineQuery(additionalFilter, if (includeFilterByChatInBehaviourSubContext) InlineQueryFilterByUser else null, markerFactory, scenarioReceiver)

@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onBaseInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<BaseInlineQuery>? = null,
    markerFactory: MarkerFactory<in BaseInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, BaseInlineQuery>
) = onInlineQuery(additionalFilter, if (includeFilterByChatInBehaviourSubContext) InlineQueryFilterByUser else null, markerFactory, scenarioReceiver)

@Deprecated(OldAPITriggersDeprecationText)
suspend fun BehaviourContext.onLocationInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<LocationInlineQuery>? = null,
    markerFactory: MarkerFactory<in LocationInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, LocationInlineQuery>
) = onInlineQuery(additionalFilter, if (includeFilterByChatInBehaviourSubContext) InlineQueryFilterByUser else null, markerFactory, scenarioReceiver)



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
suspend fun BehaviourContext.onAnyInlineQuery(
    initialFilter: SimpleFilter<InlineQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, InlineQuery, Update>? = InlineQueryFilterByUser,
    markerFactory: MarkerFactory<in InlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineQuery>
) = onInlineQuery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)


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
suspend fun BehaviourContext.onBaseInlineQuery(
    initialFilter: SimpleFilter<BaseInlineQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, BaseInlineQuery, Update>? = InlineQueryFilterByUser,
    markerFactory: MarkerFactory<in BaseInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, BaseInlineQuery>
) = onInlineQuery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)


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
suspend fun BehaviourContext.onLocationInlineQuery(
    initialFilter: SimpleFilter<LocationInlineQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, LocationInlineQuery, Update>? = InlineQueryFilterByUser,
    markerFactory: MarkerFactory<in LocationInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, LocationInlineQuery>
) = onInlineQuery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
