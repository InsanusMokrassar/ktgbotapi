package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptionsAsync
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend fun <BC : BehaviourContext, T> BC.on(
    markerFactory: MarkerFactory<in T, Any>?,
    initialFilter: SimpleFilter<T>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, T, Update>? = null,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, T>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, T>,
    updateToData: (Update) -> List<T>?
) = flowsUpdatesFilter.expectFlow(
    bot,
    filter = initialFilter ?.let {
        {
            updateToData(it) ?.mapNotNull { data ->
                if (initialFilter(data)) it to data else null
            } ?: emptyList()
        }
    } ?: {
        updateToData(it) ?.map { data ->
            it to data
        } ?: emptyList()
    }
).run {
    val localSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, T> = additionalSubcontextInitialAction ?.let { _ ->
        { update, it ->
            additionalSubcontextInitialAction(update, it)
            subcontextInitialAction(update)
        }
    } ?: { update, _ ->
        subcontextInitialAction(update)
    }
    val handler: suspend (Pair<Update, T>) -> Unit = subcontextUpdatesFilter ?.let {
        { (update, triggerData) ->
            createSubContextAndDoSynchronouslyWithUpdatesFilter {
                if (subcontextUpdatesFilter(this, triggerData, update)) {
                    localSubcontextInitialAction(update, triggerData)
                    scenarioReceiver(triggerData)
                }
            }
        }
    } ?: { (update, triggerData) ->
        localSubcontextInitialAction(update, triggerData)
        createSubContextAndDoSynchronouslyWithUpdatesFilter(behaviourContextReceiver = { scenarioReceiver(triggerData) })
    }
    markerFactory ?.let {
        subscribeSafelyWithoutExceptionsAsync(
            scope,
            { markerFactory(it.second) },
            block = handler
        )
    } ?: subscribeSafelyWithoutExceptions(scope) {
        scope.launchSafelyWithoutExceptions {
            handler(it)
        }
    }
}
