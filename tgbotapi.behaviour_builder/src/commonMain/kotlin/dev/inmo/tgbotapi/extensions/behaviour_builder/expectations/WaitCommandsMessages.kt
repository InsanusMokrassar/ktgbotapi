package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar.doWithRegistration
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.TelegramBotCommandsDefaults
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithArgs
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithArgsSources
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithNamedArgs
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import kotlinx.coroutines.flow.*

/**
 * Will filter all the messages and include required commands with [commandRegex].
 *
 * * In case you wish to get only the commands at the start of message, use [requireCommandAtStart]
 * * In case you wish to exclude messages with more than one command, you may use [requireSingleCommand]
 * * In case you wish to exclude messages with commands params, you may use [requireCommandsWithoutParams]
 */
suspend fun BehaviourContext.waitCommandMessage(
    commandRegex: Regex,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = channelFlow {
    triggersHolder.handleableCommandsHolder.doWithRegistration(
        commandRegex,
    ) {
        waitTextMessage(initRequest, errorFactory).filter {
            it.content.textSources.any { it.botCommandTextSourceOrNull() ?.command ?.matches(commandRegex) == true }
        }.collect {
            send(it)
        }
    }
}

suspend fun BehaviourContext.waitCommandMessage(
    command: String,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitCommandMessage(Regex(command), initRequest, errorFactory)

suspend fun BehaviourContext.waitCommandMessage(
    botCommand: BotCommand,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitCommandMessage(botCommand.command, initRequest, errorFactory)

fun Flow<CommonMessage<TextContent>>.requireCommandAtStart() = filter {
    it.content.textSources.firstOrNull() is BotCommandTextSource
}

/**
 * Subsequent [Flow] will retrieve only messages with ONE [BotCommandTextSource]. It does not guarantee that this
 * [BotCommandTextSource] will be at the start of the message
 *
 * @see requireCommandAtStart
 */
fun Flow<CommonMessage<TextContent>>.requireSingleCommand() = filter {
    var count = 0

    it.content.textSources.forEach {
        if (it is BotCommandTextSource) {
            count++
            if (count > 1) {
                return@filter false
            }
        }
    }

    count == 1
}

/**
 * Subsequent [Flow] will retrieve only messages without [TextContent.textSources] which are not [BotCommandTextSource]
 */
fun Flow<CommonMessage<TextContent>>.requireCommandsWithoutParams() = filter {
    it.content.textSources.none { it !is BotCommandTextSource }
}

/**
 * Uses [parseCommandsWithArgsSources] on incoming text sources and map them with [CommonMessage]
 */
fun Flow<CommonMessage<TextContent>>.commandsWithParams(): Flow<Pair<CommonMessage<TextContent>, List<Pair<BotCommandTextSource, Array<TextSource>>>>> = mapNotNull {
    it to it.content.textSources.parseCommandsWithArgsSources().toList()
}

/**
 * Uses [parseCommandsWithArgs] on incoming text sources and map them with [CommonMessage]
 */
fun Flow<CommonMessage<TextContent>>.commandsWithArgs(
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
): Flow<Pair<CommonMessage<TextContent>, List<Pair<String, Array<String>>>>> = mapNotNull {
    val commandsWithArgs = it.content.textSources.parseCommandsWithArgs(argsSeparator).toList().ifEmpty {
        return@mapNotNull null
    }

    it to commandsWithArgs
}

/**
 * Uses [parseCommandsWithArgs] on incoming text sources and map them with [CommonMessage]
 */
fun Flow<CommonMessage<TextContent>>.commandsWithArgs(
    argsSeparator: String,
): Flow<Pair<CommonMessage<TextContent>, List<Pair<String, Array<String>>>>> = commandsWithArgs(Regex(argsSeparator))

/**
 * Uses [parseCommandsWithNamedArgs] on incoming text sources and map them with [CommonMessage]
 */
fun Flow<CommonMessage<TextContent>>.commandsWithNamedArgs(
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
): Flow<Pair<CommonMessage<TextContent>, List<Pair<String, List<Pair<String, String>>>>>> = mapNotNull {
    val commandsWithArgs = it.content.textSources.parseCommandsWithNamedArgs(argsSeparator, nameArgSeparator).toList().ifEmpty {
        return@mapNotNull null
    }

    it to commandsWithArgs
}

/**
 * Uses [parseCommandsWithNamedArgs] on incoming text sources and map them with [CommonMessage]
 */
fun Flow<CommonMessage<TextContent>>.commandsWithNamedArgs(
    argsSeparator: String,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
): Flow<Pair<CommonMessage<TextContent>, List<Pair<String, List<Pair<String, String>>>>>> = commandsWithNamedArgs(
    Regex(argsSeparator),
    nameArgSeparator,
)

/**
 * Flat [commandsWithParams]. Each [Pair] of [BotCommandTextSource] and its [Array] of arg text sources will
 * be associated with its source message
 */
fun Flow<CommonMessage<TextContent>>.flattenCommandsWithParams() = commandsWithParams().flatMapConcat { (message, commandsWithParams) ->
    commandsWithParams.map {
        message to it
    }.asFlow()
}

/**
 * Use [flattenCommandsWithParams] and filter out the commands which do not [matches] to [commandRegex]
 */
fun Flow<CommonMessage<TextContent>>.commandParams(commandRegex: Regex) = flattenCommandsWithParams().filter { (_, commandWithParams) ->
    commandWithParams.first.command.matches(commandRegex)
}
