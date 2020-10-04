package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.games

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.SetGameScoreByInlineMessageId
import com.github.insanusmokrassar.TelegramBotAPI.types.*

suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    inlineMessageId: InlineMessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = execute(
    SetGameScoreByInlineMessageId(
        userId, score, inlineMessageId, force, disableEditMessage
    )
)

suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    inlineMessageId: InlineMessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(user.id, score, inlineMessageId, force, disableEditMessage)
