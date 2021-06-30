package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptionsAsync
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserInlineQueryMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asInlineQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.InlineQueries.query.*

internal suspend inline fun <reified T : InlineQuery> BehaviourContext.onInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (T) -> Boolean)? = null,
    markerFactory: MarkerFactory<in T, Any> = ByUserInlineQueryMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, T>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asInlineQueryUpdate() ?.data ?.let { query ->
        if (query is T) {
            if (additionalFilter == null || additionalFilter(query)) query else null
        } else {
            null
        }
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptionsAsync(
    scope,
    markerFactory::invoke
) { triggerQuery ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerQuery.from.id.chatId }
        } else {
            null
        },
        stopOnCompletion = false
    ) {
        scenarioReceiver(triggerQuery)
    }
}


suspend fun BehaviourContext.onAnyInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (InlineQuery) -> Boolean)? = null,
    markerFactory: MarkerFactory<in InlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineQuery>
) = onInlineQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)


suspend fun BehaviourContext.onBaseInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (BaseInlineQuery) -> Boolean)? = null,
    markerFactory: MarkerFactory<in BaseInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, BaseInlineQuery>
) = onInlineQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)


suspend fun BehaviourContext.onLocationInlineQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (LocationInlineQuery) -> Boolean)? = null,
    markerFactory: MarkerFactory<in LocationInlineQuery, Any> = ByUserInlineQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, LocationInlineQuery>
) = onInlineQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
