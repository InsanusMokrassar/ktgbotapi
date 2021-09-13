package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptionsAsync
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified T> BehaviourContext.on(
    markerFactory: MarkerFactory<in T, Any>,
    noinline initialFilter: SimpleFilter<T>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>? = null,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, T>,
    noinline updateToData: (Update) -> List<T>?
) = flowsUpdatesFilter.expectFlow(bot) {
    updateToData(it) ?.mapNotNull { data ->
        if (initialFilter ?.invoke(data) != false) data else null
    } ?: emptyList()
}.subscribeSafelyWithoutExceptionsAsync(
    scope,
    markerFactory::invoke
) { triggerData ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = subcontextUpdatesFilter ?.toOneType(triggerData),
        stopOnCompletion = false
    ) {
        scenarioReceiver(triggerData)
    }
}
