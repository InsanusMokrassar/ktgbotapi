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
import dev.inmo.tgbotapi.extensions.utils.extensions.TelegramBotCommandsDefaults
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithArgs
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithNamedArgs
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.Job

internal fun <BC : BehaviourContext> BC.commandUncounted(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
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
    additionalSubcontextInitialAction,
    scenarioReceiver
)

suspend fun <BC : BehaviourContext> BC.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = runCatching {
    commandUncounted(
        commandRegex,
        requireOnlyCommandInMessage,
        initialFilter,
        subcontextUpdatesFilter,
        markerFactory,
        additionalSubcontextInitialAction,
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

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.command(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
) = command(command.toRegex(), requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.command(
    botCommand: BotCommand,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
) = command(botCommand.command, requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommand(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = command(commandRegex, requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommand(
    command: String,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = onCommand(command.toRegex(), requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommand(
    botCommand: BotCommand,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
): Job = onCommand(botCommand.command, requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.commandWithArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = command(
    commandRegex,
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory
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
suspend fun <BC : BehaviourContext> BC.commandWithArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = commandWithArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.commandWithArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
) = commandWithArgs(
    botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.commandWithNamedArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = command(
    commandRegex,
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
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
suspend fun <BC : BehaviourContext> BC.commandWithNamedArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = commandWithNamedArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.commandWithNamedArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = commandWithNamedArgs(
    botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommandWithArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = commandWithArgs(
    commandRegex = commandRegex,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommandWithArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = onCommandWithArgs(
    commandRegex = command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommandWithArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Array<String>>
): Job = onCommandWithArgs(
    command = botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommandWithNamedArgs(
    commandRegex: Regex,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = commandWithNamedArgs(
    commandRegex,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    scenarioReceiver = scenarioReceiver,
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommandWithNamedArgs(
    command: String,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = onCommandWithNamedArgs(
    command.toRegex(),
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    scenarioReceiver = scenarioReceiver
)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onCommandWithNamedArgs(
    botCommand: BotCommand,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, List<Pair<String, String>>>
) = onCommandWithNamedArgs(
    botCommand.command,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
    argsSeparator = argsSeparator,
    nameArgSeparator = nameArgSeparator,
    scenarioReceiver = scenarioReceiver
)
