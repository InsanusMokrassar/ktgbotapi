@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.botInfo
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.containsCommand
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.botCommandTextSourceOrNull
import dev.inmo.tgbotapi.extensions.utils.extensions.TelegramBotCommandsDefaults
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithArgs
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithNamedArgs
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.launchWithBotLogger
import kotlinx.coroutines.Job
import kotlinx.coroutines.job

internal fun <BC : BehaviourContext> BC.commandUncounted(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    excludeCommandsToOtherBots: Boolean = true,
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
        if (excludeCommandsToOtherBots) {
            it * lambda@{
                with(it.content.textSources) {
                    containsCommand(commandRegex)
                }
            }
        } else {
            it
        }
    }.let {
        initialFilter ?.times(it) ?: it
    },
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

fun <BC : BehaviourContext> BC.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = launchInNewSubContext {
    runCatching {
       this@launchInNewSubContext.commandUncounted(
           commandRegex = commandRegex,
           requireOnlyCommandInMessage = requireOnlyCommandInMessage,
           initialFilter = initialFilter,
           subcontextUpdatesFilter = subcontextUpdatesFilter,
           markerFactory = markerFactory,
           additionalSubcontextInitialAction = additionalSubcontextInitialAction,
           excludeCommandsToOtherBots = excludeCommandsToOtherBots,
           scenarioReceiver = scenarioReceiver
        )
    }.onFailure {
        triggersHolder.handleableCommandsHolder.unregisterHandleable(commandRegex)
    }.onSuccess {
        triggersHolder.handleableCommandsHolder.registerHandleable(commandRegex)
        it.invokeOnCompletion {
            runCatching {
                launchWithBotLogger {
                    triggersHolder.handleableCommandsHolder.unregisterHandleable(commandRegex)
                }
            }
        }
    }.getOrThrow()
}

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.command(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
) = command(
    commandRegex = command.toRegex(),
    requireOnlyCommandInMessage = requireOnlyCommandInMessage,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    additionalSubcontextInitialAction = additionalSubcontextInitialAction,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.command(
    botCommand: BotCommand,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
) = command(
    command = botCommand.command,
    requireOnlyCommandInMessage = requireOnlyCommandInMessage,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    additionalSubcontextInitialAction = additionalSubcontextInitialAction,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommand(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = command(
    commandRegex = commandRegex,
    requireOnlyCommandInMessage = requireOnlyCommandInMessage,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    additionalSubcontextInitialAction = additionalSubcontextInitialAction,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommand(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = onCommand(
    commandRegex = command.toRegex(),
    requireOnlyCommandInMessage = requireOnlyCommandInMessage,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    additionalSubcontextInitialAction = additionalSubcontextInitialAction,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommand(
    botCommand: BotCommand,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = onCommand(
    command = botCommand.command,
    requireOnlyCommandInMessage = requireOnlyCommandInMessage,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    additionalSubcontextInitialAction = additionalSubcontextInitialAction,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.commandWithArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = command(
    commandRegex,
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
) {
    val args = it.parseCommandsWithArgs(argsSeparator = argsSeparator).let { commandsWithArgs ->
        val key = commandsWithArgs.keys.firstOrNull { it.matches(commandRegex) } ?: return@let null
        commandsWithArgs[key]
    } ?: emptyArray()
    scenarioReceiver(it, args)
}

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.commandWithArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = commandWithArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.commandWithArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = commandWithArgs(
    botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.commandWithNamedArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = command(
    commandRegex,
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    markerFactory = markerFactory
) {
    val args = it.parseCommandsWithNamedArgs(argsSeparator = argsSeparator, nameArgSeparator = nameArgSeparator).let { commandsWithArgs ->
        val key = commandsWithArgs.keys.firstOrNull { it.matches(commandRegex) } ?: return@let null
        commandsWithArgs[key]
    } ?: emptyList()
    scenarioReceiver(it, args)
}

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.commandWithNamedArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = commandWithNamedArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.commandWithNamedArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = commandWithNamedArgs(
    botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommandWithArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = commandWithArgs(
    commandRegex = commandRegex,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommandWithArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = onCommandWithArgs(
    commandRegex = command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommandWithArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = onCommandWithArgs(
    command = botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommandWithNamedArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = commandWithNamedArgs(
    commandRegex,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver,
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommandWithNamedArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = onCommandWithNamedArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onCommandWithNamedArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    excludeCommandsToOtherBots: Boolean = true,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = onCommandWithNamedArgs(
    botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    scenarioReceiver = scenarioReceiver
)
