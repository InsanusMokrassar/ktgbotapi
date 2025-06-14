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
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = channelFlow {
    triggersHolder.handleableCommandsHolder.doWithRegistration(
        commandRegex
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
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommandMessage(Regex(command), initRequest, errorFactory)

suspend fun BehaviourContext.waitCommandMessage(
    botCommand: BotCommand,
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommandMessage(botCommand.command, initRequest, errorFactory)
