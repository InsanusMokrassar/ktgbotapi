package dev.inmo.tgbotapi.extensions.api.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.games.SetGameScoreByInlineMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser

public suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    inlineMessageId: InlineMessageId,
    force: Boolean = false,
    disableEditMessage: Boolean = false
): Boolean = execute(
    SetGameScoreByInlineMessageId(
        userId, score, inlineMessageId, force, disableEditMessage
    )
)

public suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    inlineMessageId: InlineMessageId,
    force: Boolean = false,
    disableEditMessage: Boolean = false
): Boolean = setGameScore(user.id, score, inlineMessageId, force, disableEditMessage)
