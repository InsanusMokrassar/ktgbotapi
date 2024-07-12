package dev.inmo.tgbotapi.extensions.api.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.games.GetGameHighScoresByChat
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.games.GameHighScore
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.GameContent

public suspend fun TelegramBot.getGameScore(
    userId: UserId,
    chatId: IdChatIdentifier,
    messageId: MessageId
): List<GameHighScore> = execute(
    GetGameHighScoresByChat(userId, chatId, messageId)
)

public suspend fun TelegramBot.getGameScore(
    user: CommonUser,
    chatId: IdChatIdentifier,
    messageId: MessageId
): List<GameHighScore> = getGameScore(
    user.id, chatId, messageId
)

public suspend fun TelegramBot.getGameScore(
    userId: UserId,
    chat: Chat,
    messageId: MessageId
): List<GameHighScore> = getGameScore(
    userId, chat.id, messageId
)

public suspend fun TelegramBot.getGameScore(
    user: CommonUser,
    chat: Chat,
    messageId: MessageId
): List<GameHighScore> = getGameScore(
    user.id, chat.id, messageId
)

public suspend fun TelegramBot.getGameScore(
    userId: UserId,
    message: ContentMessage<GameContent>
): List<GameHighScore> = getGameScore(
    userId, message.chat.id, message.messageId
)

public suspend fun TelegramBot.getGameScore(
    user: CommonUser,
    message: ContentMessage<GameContent>
): List<GameHighScore> = getGameScore(
    user.id, message.chat.id, message.messageId
)
