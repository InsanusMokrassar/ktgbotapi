package dev.inmo.tgbotapi.extensions.api.inline

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.inline.SavePreparedInlineMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.prepared.PreparedInlineMessage
import dev.inmo.tgbotapi.types.chat.User

public suspend fun TelegramBot.savePreparedInlineMessage(
    userId: UserId,
    result: InlineQueryResult,
    allowSendToUsers: Boolean = false,
    allowSendToBots: Boolean = false,
    allowSendToGroups: Boolean = false,
    allowSendToChannels: Boolean = false,
): PreparedInlineMessage = execute(
    SavePreparedInlineMessage(
        userId = userId,
        result = result,
        allowSendToUsers = allowSendToUsers,
        allowSendToBots = allowSendToBots,
        allowSendToGroups = allowSendToGroups,
        allowSendToChannels = allowSendToChannels,
    ),
)

public suspend fun TelegramBot.savePreparedInlineMessage(
    user: User,
    result: InlineQueryResult,
    allowSendToUsers: Boolean = false,
    allowSendToBots: Boolean = false,
    allowSendToGroups: Boolean = false,
    allowSendToChannels: Boolean = false,
): PreparedInlineMessage = savePreparedInlineMessage(
    userId = user.id,
    result = result,
    allowSendToUsers = allowSendToUsers,
    allowSendToBots = allowSendToBots,
    allowSendToGroups = allowSendToGroups,
    allowSendToChannels = allowSendToChannels,
)
