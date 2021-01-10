package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.extensions.utils.onlyTextContentMessages
import dev.inmo.tgbotapi.extensions.utils.shortcuts.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.BotCommandTextSource
import dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow

/**
 * Convert incoming [dev.inmo.tgbotapi.types.message.abstracts.ContentMessage.content] of
 * messages with [fullEntitiesList] and check that incoming message contains ONLY ONE [TextSource] and that is
 * [BotCommandTextSource]. Besides, it is checking that [BotCommandTextSource.command] [Regex.matches] with incoming
 * [commandRegex]
 *
 * @return The same message in case if it contains only [BotCommandTextSource] with [Regex.matches]
 * [BotCommandTextSource.command]
 *
 * @see fullEntitiesList
 * @see asContentMessagesFlow
 * @see onlyTextContentMessages
 */
fun <T : BaseSentMessageUpdate> Flow<T>.filterExactCommands(
    commandRegex: Regex
) = textMessages().filterExactCommands(commandRegex)

/**
 * Convert incoming [dev.inmo.tgbotapi.types.message.abstracts.ContentMessage.content] of
 * messages with [fullEntitiesList] and check that incoming message contains [BotCommandTextSource]. Besides, it is
 * checking that [BotCommandTextSource.command] [Regex.matches] with incoming [commandRegex]
 *
 * @return The same message in case if it contains somewhere in text [BotCommandTextSource] with [Regex.matches]
 * [BotCommandTextSource.command]
 *
 * @see fullEntitiesList
 * @see asContentMessagesFlow
 * @see onlyTextContentMessages
 */
fun <T : BaseSentMessageUpdate> Flow<T>.filterCommandsInsideTextMessages(
    commandRegex: Regex
) = textMessages().filterCommandsInsideTextMessages(commandRegex)

/**
 * Convert incoming [dev.inmo.tgbotapi.types.message.abstracts.ContentMessage.content] of
 * messages with [fullEntitiesList] and check that incoming message contains first [TextSource] as
 * [BotCommandTextSource]. Besides, it is checking that [BotCommandTextSource.command] [Regex.matches] with incoming
 * [commandRegex] and for other [TextSource] objects used next rules: all incoming text sources will be passed as is,
 * [RegularTextSource] will be split by " " for several [RegularTextSource] which will contains not empty args without
 * spaces.
 *
 * @return Paired original message and converted list with first entity [BotCommandTextSource] and than all others
 * according to rules in description
 *
 * @see fullEntitiesList
 * @see asContentMessagesFlow
 * @see onlyTextContentMessages
 */
fun <T : BaseSentMessageUpdate> Flow<T>.filterCommandsWithArgs(
    commandRegex: Regex
): Flow<Pair<ContentMessage<TextContent>, List<TextSource>>> = textMessages().filterCommandsWithArgs(commandRegex)
