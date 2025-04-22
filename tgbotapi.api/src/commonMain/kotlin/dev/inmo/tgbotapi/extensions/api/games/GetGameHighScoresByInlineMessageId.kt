package dev.inmo.tgbotapi.extensions.api.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.games.GetGameHighScoresByInlineMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.games.GameHighScore

public suspend fun TelegramBot.getGameScore(
    userId: UserId,
    inlineMessageId: InlineMessageId,
): List<GameHighScore> =
    execute(
        GetGameHighScoresByInlineMessageId(
            userId,
            inlineMessageId,
        ),
    )

public suspend fun TelegramBot.getGameScore(
    user: CommonUser,
    inlineMessageId: InlineMessageId,
): List<GameHighScore> = getGameScore(user.id, inlineMessageId)
