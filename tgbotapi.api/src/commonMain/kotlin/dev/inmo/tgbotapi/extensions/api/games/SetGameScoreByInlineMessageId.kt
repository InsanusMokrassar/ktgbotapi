package dev.inmo.tgbotapi.extensions.api.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.games.SetGameScoreByInlineMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser

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
