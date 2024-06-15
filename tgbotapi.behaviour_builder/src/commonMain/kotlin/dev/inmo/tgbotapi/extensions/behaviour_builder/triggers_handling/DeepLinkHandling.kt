@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.types.message.textsources.RegularTextSource
import dev.inmo.tgbotapi.types.update.abstracts.Update
import io.ktor.http.decodeURLQueryComponent
import kotlinx.coroutines.Job

private val startRegex = Regex("start")
suspend fun <BC : BehaviourContext> BC.onDeepLink(
    initialFilter: SimpleFilter<Pair<TextMessage, String>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, Pair<TextMessage, String>, Update>? = { (message, _), update -> MessageFilterByChat(this, message, update) },
    markerFactory: MarkerFactory<Pair<TextMessage, String>, Any>? = MarkerFactory { (message, _) -> ByChatMessageMarkerFactory(message) },
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, Pair<TextMessage, String>>
): Job = on(
    markerFactory,
    SimpleFilter<Pair<TextMessage, String>> { (message, _) ->
        message.content.textSources.size == 2
            && message.content.textSources.firstOrNull() ?.asBotCommandTextSource() ?.command == "start"
            && message.content.textSources.getOrNull(1) is RegularTextSource
    } * initialFilter,
    subcontextUpdatesFilter,
    scenarioReceiver,
) {
    (it.messageUpdateOrNull()) ?.data ?.commonMessageOrNull() ?.withContentOrNull<TextContent>() ?.let { message ->
        message to (message.content.textSources.getOrNull(1) ?.source ?.removePrefix(" ") ?.decodeURLQueryComponent() ?: return@let null)
    } ?.let(::listOfNotNull)
}.also {
    triggersHolder.handleableCommandsHolder.registerHandleable(startRegex)
    it.invokeOnCompletion {
        this@onDeepLink.launchSafelyWithoutExceptions { triggersHolder.handleableCommandsHolder.unregisterHandleable(startRegex) }
    }
}

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onDeepLink(
    regex: Regex,
    initialFilter: SimpleFilter<Pair<TextMessage, String>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, Pair<TextMessage, String>, Update>? = { (message, _), update -> MessageFilterByChat(this, message, update) },
    markerFactory: MarkerFactory<Pair<TextMessage, String>, Any>? = MarkerFactory { (message, _) -> ByChatMessageMarkerFactory(message) },
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, Pair<TextMessage, String>>
): Job {
    val internalFilter = SimpleFilter<Pair<TextMessage, String>> {
        regex.matches(it.second)
    }
    return onDeepLink(initialFilter ?.let { internalFilter * it } ?: internalFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
}

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onDeepLink(
    deepLink: String,
    initialFilter: SimpleFilter<Pair<TextMessage, String>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, Pair<TextMessage, String>, Update>? = { (message, _), update -> MessageFilterByChat(this, message, update) },
    markerFactory: MarkerFactory<Pair<TextMessage, String>, Any>? = MarkerFactory { (message, _) -> ByChatMessageMarkerFactory(message) },
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, Pair<TextMessage, String>>
): Job = onDeepLink(Regex("^$deepLink$"), initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
