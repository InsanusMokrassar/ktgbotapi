package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asBotCommandTextSource
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithParams
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import kotlinx.coroutines.Job

suspend fun BehaviourContext.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = onText(
    includeFilterByChatInBehaviourSubContext,
    { message ->
        val content = message.content
        val textSources = content.textSources
        val sizeRequirement = if (requireOnlyCommandInMessage) {
            textSources.size == 1
        } else {
            true
        }
        sizeRequirement && textSources.any {
            commandRegex.matches(it.asBotCommandTextSource() ?.command ?: return@any false)
        } && (additionalFilter ?.invoke(message) != false)
    },
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.command(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
) = command(command.toRegex(), requireOnlyCommandInMessage, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommand(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = command(commandRegex, requireOnlyCommandInMessage, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommand(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = onCommand(command.toRegex(), requireOnlyCommandInMessage, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.commandWithArgs(
    commandRegex: Regex,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
) = command(
    commandRegex,
    requireOnlyCommandInMessage = false,
    includeFilterByChatInBehaviourSubContext = includeFilterByChatInBehaviourSubContext,
    additionalFilter = additionalFilter,
    markerFactory = markerFactory
) {
    val args = it.parseCommandsWithParams().let { commandsWithArgs ->
        val key = commandsWithArgs.keys.firstOrNull { it.matches(commandRegex) } ?: return@let null
        commandsWithArgs[key]
    } ?: emptyArray()
    scenarioReceiver(it, args)
}

suspend fun BehaviourContext.commandWithArgs(
    command: String,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
) = commandWithArgs(
    command.toRegex(),
    includeFilterByChatInBehaviourSubContext = includeFilterByChatInBehaviourSubContext,
    additionalFilter = additionalFilter,
    markerFactory = markerFactory,
    scenarioReceiver = scenarioReceiver
)

suspend inline fun BehaviourContext.onCommandWithArgs(
    commandRegex: Regex,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
): Job = commandWithArgs(commandRegex, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommandWithArgs(
    command: String,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
): Job = onCommandWithArgs(command.toRegex(), includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
