package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.onlyTextContentMessages
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.BotCommandTextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.fullEntitiesList
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.*

fun <T : BaseSentMessageUpdate> Flow<T>.filterExactCommands(
    commandRegex: Regex
) = asContentMessagesFlow().onlyTextContentMessages().filter { contentMessage ->
    (contentMessage.content.fullEntitiesList().singleOrNull() as? BotCommandTextSource) ?.let { commandRegex.matches(it.command) } == true
}

fun <T : BaseSentMessageUpdate> Flow<T>.filterCommandsInsideTextMessages(
    commandRegex: Regex
) = asContentMessagesFlow().onlyTextContentMessages().filter { contentMessage ->
    contentMessage.content.fullEntitiesList().any {
        (it as? BotCommandTextSource) ?.let { commandRegex.matches(it.command) } == true
    }
}

/**
 * @return Result [Flow] will emit all [TextSource]s to the collector ONLY IN CASE if first [TextSource] is
 * [BotCommandTextSource] and its [BotCommandTextSource.command] is [Regex.matches] to incoming [commandRegex]. Internal
 * behaviour contains next rules: all incoming text sources will be passed as is, [RegularTextSource] will be divided
 * by " " for several [RegularTextSource] which will contains not empty args without spaces
 */
fun <T : BaseSentMessageUpdate> Flow<T>.filterCommandsWithArgs(
    commandRegex: Regex
): Flow<List<TextSource>> = asContentMessagesFlow().onlyTextContentMessages().mapNotNull { contentMessage ->
    val allEntities = contentMessage.content.fullEntitiesList()
    (allEntities.firstOrNull() as? BotCommandTextSource) ?.let {
        if (commandRegex.matches(it.command)) {
            allEntities.flatMap {
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
