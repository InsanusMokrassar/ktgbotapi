package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.games

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.GetGameHighScoresByInlineMessageId
import com.github.insanusmokrassar.TelegramBotAPI.types.*

suspend fun RequestsExecutor.getGameScore(
    userId: UserId,
    inlineMessageId: InlineMessageIdentifier
) = execute(
    GetGameHighScoresByInlineMessageId(
        userId, inlineMessageId
    )
)

suspend fun RequestsExecutor.getGameScore(
    user: CommonUser,
    inlineMessageId: InlineMessageIdentifier
) = getGameScore(user.id, inlineMessageId)
