@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.asBotCommandTextSource
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithParams
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.Job


suspend fun <BC : BehaviourContext> BC.unhandledCommand(
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextContent>>
): Job = onText(
    CommonMessageFilter<TextContent> { message ->
        val content = message.content
        val textSources = content.textSources
        val sizeRequirement = if (requireOnlyCommandInMessage) {
            textSources.size == 1
        } else {
            true
        }
        sizeRequirement && textSources.any {
            val command = it.asBotCommandTextSource() ?.command ?: return@any false
            !triggersHolder.handleableCommandsHolder.isHandled(command)
        }
    }.let {
        initialFilter ?.times(it) ?: it
    },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

suspend fun <BC : BehaviourContext> BC.onUnhandledCommand(
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextContent>>
): Job = unhandledCommand(requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

suspend fun <BC : BehaviourContext> BC.unhandledCommandWithArgs(
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, CommonMessage<TextContent>, Map<String, Array<String>>>
) = onUnhandledCommand(
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory
) {
    val args = it.parseCommandsWithParams().let { commandsWithArgs ->
        commandsWithArgs
    }
    scenarioReceiver(it, args)
}

suspend fun <BC : BehaviourContext> BC.onUnhandledCommandWithArgs(
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, CommonMessage<TextContent>, Map<String, Array<String>>>
): Job = unhandledCommandWithArgs(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
