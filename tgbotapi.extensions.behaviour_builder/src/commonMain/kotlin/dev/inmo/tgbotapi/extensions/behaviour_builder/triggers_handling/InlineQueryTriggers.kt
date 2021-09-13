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


suspend fun BehaviourContext.onAnyInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<InlineQuery>? = null,
    markerFactory: MarkerFactory<in InlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineQuery>
) = onInlineQuery(additionalFilter, if (includeFilterByChatInBehaviourSubContext) InlineQueryFilterByUser else null, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.onBaseInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<BaseInlineQuery>? = null,
    markerFactory: MarkerFactory<in BaseInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, BaseInlineQuery>
) = onInlineQuery(additionalFilter, if (includeFilterByChatInBehaviourSubContext) InlineQueryFilterByUser else null, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.onLocationInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<LocationInlineQuery>? = null,
    markerFactory: MarkerFactory<in LocationInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, LocationInlineQuery>
) = onInlineQuery(additionalFilter, if (includeFilterByChatInBehaviourSubContext) InlineQueryFilterByUser else null, markerFactory, scenarioReceiver)


suspend fun BehaviourContext.onAnyInlineQuery(
    initialFilter: SimpleFilter<InlineQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, InlineQuery, Update>? = InlineQueryFilterByUser,
    markerFactory: MarkerFactory<in InlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineQuery>
) = onInlineQuery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.onBaseInlineQuery(
    initialFilter: SimpleFilter<BaseInlineQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, BaseInlineQuery, Update>? = InlineQueryFilterByUser,
    markerFactory: MarkerFactory<in BaseInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, BaseInlineQuery>
) = onInlineQuery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.onLocationInlineQuery(
    initialFilter: SimpleFilter<LocationInlineQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, LocationInlineQuery, Update>? = InlineQueryFilterByUser,
    markerFactory: MarkerFactory<in LocationInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, LocationInlineQuery>
) = onInlineQuery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
