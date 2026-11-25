package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.WidgetQueryFilterByUser
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserWidgetQueryMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.types.update.WidgetQueryUpdate
import dev.inmo.tgbotapi.types.update.SharedWidgetQueryUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.widgets.*

/** Handles queries from a widget sent by the bot. */
fun <BC : BehaviourContext> BC.onWidgetQuery(
    initialFilter: SimpleFilter<WidgetQuery>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, WidgetQuery, Update>? = WidgetQueryFilterByUser,
    markerFactory: MarkerFactory<in WidgetQuery, Any>? = ByUserWidgetQueryMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, WidgetQuery>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, WidgetQuery>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it as? WidgetQueryUpdate)?.data?.let(::listOf)
}

/** Handles queries from a forwarded shared widget. */
fun <BC : BehaviourContext> BC.onSharedWidgetQuery(
    initialFilter: SimpleFilter<SharedWidgetQuery>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, SharedWidgetQuery, Update>? = WidgetQueryFilterByUser,
    markerFactory: MarkerFactory<in SharedWidgetQuery, Any>? = ByUserWidgetQueryMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, SharedWidgetQuery>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, SharedWidgetQuery>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    (it as? SharedWidgetQueryUpdate)?.data?.let(::listOf)
}
