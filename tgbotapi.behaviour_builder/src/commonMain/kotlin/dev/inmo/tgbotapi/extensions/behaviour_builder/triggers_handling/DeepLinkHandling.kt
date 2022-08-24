@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitDeepLinks
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithParams
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.types.message.textsources.RegularTextSource
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filter

suspend fun <BC : BehaviourContext> BC.onDeepLink(
    initialFilter: SimpleFilter<Pair<TextMessage, String>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, Pair<TextMessage, String>, Update> = { (message, _), update -> MessageFilterByChat(this, message, update) },
    markerFactory: MarkerFactory<Pair<TextMessage, String>, Any> = MarkerFactory { (message, _) -> ByChatMessageMarkerFactory(message) },
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
        message to message.content.textSources[1].source
    } ?.let(::listOfNotNull)
}
