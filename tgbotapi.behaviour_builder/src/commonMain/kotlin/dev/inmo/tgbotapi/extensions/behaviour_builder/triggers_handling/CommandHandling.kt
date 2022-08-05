@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.runCatchingSafely
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.botCommandTextSourceOrNull
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithParams
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.Job

internal suspend fun <BC : BehaviourContext> BC.commandUncounted(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
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
            commandRegex.matches(it.botCommandTextSourceOrNull() ?.command ?: return@any false)
        }
    }.let {
        initialFilter ?.times(it) ?: it
    },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

suspend fun <BC : BehaviourContext> BC.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = runCatchingSafely {
    commandUncounted(
        commandRegex,
        requireOnlyCommandInMessage,
        initialFilter,
        subcontextUpdatesFilter,
        markerFactory,
        scenarioReceiver
    )
}.onFailure {
    triggersHolder.handleableCommandsHolder.unregisterHandleable(commandRegex)
}.onSuccess {
    triggersHolder.handleableCommandsHolder.registerHandleable(commandRegex)
    it.invokeOnCompletion {
        runCatching {
            launchSafelyWithoutExceptions {
                triggersHolder.handleableCommandsHolder.unregisterHandleable(commandRegex)
            }
        }
    }
}.getOrThrow()

suspend fun <BC : BehaviourContext> BC.command(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
) = command(command.toRegex(), requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

suspend fun <BC : BehaviourContext> BC.onCommand(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = command(commandRegex, requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

suspend fun <BC : BehaviourContext> BC.onCommand(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = onCommand(command.toRegex(), requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

suspend fun <BC : BehaviourContext> BC.commandWithArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = command(
    commandRegex,
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory
) {
    val args = it.parseCommandsWithParams().let { commandsWithArgs ->
        val key = commandsWithArgs.keys.firstOrNull { it.matches(commandRegex) } ?: return@let null
        commandsWithArgs[key]
    } ?: emptyArray()
    scenarioReceiver(it, args)
}

suspend fun <BC : BehaviourContext> BC.commandWithArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = commandWithArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    scenarioReceiver = scenarioReceiver
)

suspend fun <BC : BehaviourContext> BC.onCommandWithArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = commandWithArgs(commandRegex, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

suspend fun <BC : BehaviourContext> BC.onCommandWithArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = onCommandWithArgs(command.toRegex(), initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
