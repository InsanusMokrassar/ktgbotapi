package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.games

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.GetGameHighScoresByInlineMessageId
import com.github.insanusmokrassar.TelegramBotAPI.types.*

suspend fun TelegramBot.getGameScore(
    userId: UserId,
    inlineMessageId: InlineMessageIdentifier
) = execute(
    GetGameHighScoresByInlineMessageId(
        userId, inlineMessageId
    )
)

suspend fun TelegramBot.getGameScore(
    user: CommonUser,
    inlineMessageId: InlineMessageIdentifier
) = getGameScore(user.id, inlineMessageId)
