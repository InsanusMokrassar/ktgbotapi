@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.asBotCommandTextSource
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithParams
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.Job

suspend fun BehaviourContext.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = onText(
    includeFilterByChatInBehaviourSubContext,
    CommonMessageFilter<TextContent> { message ->
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
    }.let {
        additionalFilter ?.times(it) ?: it
    },
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.command(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
) = command(command.toRegex(), requireOnlyCommandInMessage, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommand(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = command(commandRegex, requireOnlyCommandInMessage, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommand(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = onCommand(command.toRegex(), requireOnlyCommandInMessage, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.commandWithArgs(
    commandRegex: Regex,
    includeFilterByChatInBehaviourSubContext: Boolean,
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
    includeFilterByChatInBehaviourSubContext: Boolean,
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
    includeFilterByChatInBehaviourSubContext: Boolean,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
): Job = commandWithArgs(commandRegex, includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommandWithArgs(
    command: String,
    includeFilterByChatInBehaviourSubContext: Boolean,
    noinline additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
): Job = onCommandWithArgs(command.toRegex(), includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)


suspend fun BehaviourContext.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
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
            commandRegex.matches(it.asBotCommandTextSource() ?.command ?: return@any false)
        }
    }.let {
        initialFilter ?.times(it) ?: it
    },
    updatesFilter,
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.command(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
) = command(command.toRegex(), requireOnlyCommandInMessage, initialFilter, updatesFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommand(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    noinline initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    noinline updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = command(commandRegex, requireOnlyCommandInMessage, initialFilter, updatesFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommand(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    noinline initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    noinline updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
): Job = onCommand(command.toRegex(), requireOnlyCommandInMessage, initialFilter, updatesFilter, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.commandWithArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
) = command(
    commandRegex,
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    updatesFilter = updatesFilter,
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
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
) = commandWithArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    updatesFilter = updatesFilter,
    markerFactory = markerFactory,
    scenarioReceiver = scenarioReceiver
)

suspend inline fun BehaviourContext.onCommandWithArgs(
    commandRegex: Regex,
    noinline initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    noinline updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
): Job = commandWithArgs(commandRegex, initialFilter, updatesFilter, markerFactory, scenarioReceiver)

suspend inline fun BehaviourContext.onCommandWithArgs(
    command: String,
    noinline initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    noinline updatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTwoTypesReceiver<Unit, CommonMessage<TextContent>, Array<String>>
): Job = onCommandWithArgs(command.toRegex(), initialFilter, updatesFilter, markerFactory, scenarioReceiver)
