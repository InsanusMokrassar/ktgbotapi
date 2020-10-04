package dev.inmo.tgbotapi.extensions.api.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.games.GetGameHighScoresByInlineMessageId
import dev.inmo.tgbotapi.types.*

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
