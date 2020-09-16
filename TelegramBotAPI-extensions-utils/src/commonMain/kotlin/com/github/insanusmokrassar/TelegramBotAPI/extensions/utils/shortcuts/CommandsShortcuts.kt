package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.shortcuts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.onlyTextContentMessages
import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates.asContentMessagesFlow
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.BotCommandTextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.fullEntitiesList
import kotlinx.coroutines.flow.*

/**
 * Convert incoming [com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage.content] of
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
 * @see textMessages
 */
fun <T : ContentMessage<TextContent>> Flow<T>.filterExactCommands(
    commandRegex: Regex
) = filter { contentMessage ->
    (contentMessage.content.fullEntitiesList().singleOrNull() as? BotCommandTextSource) ?.let { commandRegex.matches(it.command) } == true
}

/**
 * Convert incoming [com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage.content] of
 * messages with [fullEntitiesList] and check that incoming message contains [BotCommandTextSource]. Besides, it is
 * checking that [BotCommandTextSource.command] [Regex.matches] with incoming [commandRegex]
 *
 * @return The same message in case if it contains somewhere in text [BotCommandTextSource] with [Regex.matches]
 * [BotCommandTextSource.command]
 *
 * @see fullEntitiesList
 * @see asContentMessagesFlow
 * @see onlyTextContentMessages
 * @see textMessages
 */
fun <T : ContentMessage<TextContent>> Flow<T>.filterCommandsInsideTextMessages(
    commandRegex: Regex
) = filter { contentMessage ->
    contentMessage.content.fullEntitiesList().any {
        (it as? BotCommandTextSource) ?.let { commandRegex.matches(it.command) } == true
    }
}

/**
 * Convert incoming [com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage.content] of
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
 * @see textMessages
 */
fun <T : ContentMessage<TextContent>> Flow<T>.filterCommandsWithArgs(
    commandRegex: Regex
) = mapNotNull { contentMessage ->
    val allEntities = contentMessage.content.fullEntitiesList()
    (allEntities.firstOrNull() as? BotCommandTextSource) ?.let {
        if (commandRegex.matches(it.command)) {
            contentMessage to allEntities.flatMap {
                when (it) {
                    is RegularTextSource -> it.source.split(" ").mapNotNull { regularTextSourcePart ->
                        if (regularTextSourcePart.isNotBlank()) {
                            RegularTextSource(regularTextSourcePart)
                        } else {
                            null
                        }
                    }
                    else -> listOf(it)
                }
            }
        } else {
            null
        }
    }
}


