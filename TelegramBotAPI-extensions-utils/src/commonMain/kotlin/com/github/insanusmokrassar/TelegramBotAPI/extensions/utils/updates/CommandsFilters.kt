package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.onlyTextContentMessages
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.BotCommandTextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.fullEntitiesList
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

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
