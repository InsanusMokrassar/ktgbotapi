package dev.inmo.tgbotapi.extensions.api.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.games.GetGameHighScoresByInlineMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser

suspend fun TelegramBot.getGameScore(
    userId: UserId,
    inlineMessageId: InlineMessageId
) = execute(
    GetGameHighScoresByInlineMessageId(
        userId, inlineMessageId
    )
)

suspend fun TelegramBot.getGameScore(
    user: CommonUser,
    inlineMessageId: InlineMessageId
) = getGameScore(user.id, inlineMessageId)
