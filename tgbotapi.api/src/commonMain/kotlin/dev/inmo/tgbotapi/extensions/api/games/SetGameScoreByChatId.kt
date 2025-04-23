package dev.inmo.tgbotapi.extensions.api.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.games.SetGameScoreByChatId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.GameContent

public suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    chatId: IdChatIdentifier,
    messageId: MessageId,
    force: Boolean = false,
    disableEditMessage: Boolean = false,
): Boolean = execute(
    SetGameScoreByChatId(userId, score, chatId, messageId, force, disableEditMessage),
)

public suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    chatId: IdChatIdentifier,
    messageId: MessageId,
    force: Boolean = false,
    disableEditMessage: Boolean = false,
): Boolean = setGameScore(
    user.id,
    score,
    chatId,
    messageId,
    force,
    disableEditMessage,
)

public suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    chat: Chat,
    messageId: MessageId,
    force: Boolean = false,
    disableEditMessage: Boolean = false,
): Boolean = setGameScore(
    userId,
    score,
    chat.id,
    messageId,
    force,
    disableEditMessage,
)

public suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    chat: Chat,
    messageId: MessageId,
    force: Boolean = false,
    disableEditMessage: Boolean = false,
): Boolean = setGameScore(
    user.id,
    score,
    chat.id,
    messageId,
    force,
    disableEditMessage,
)

public suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    message: ContentMessage<GameContent>,
    force: Boolean = false,
    disableEditMessage: Boolean = false,
): Boolean = setGameScore(
    userId,
    score,
    message.chat.id,
    message.messageId,
    force,
    disableEditMessage,
)

public suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    message: ContentMessage<GameContent>,
    force: Boolean = false,
    disableEditMessage: Boolean = false,
): Boolean = setGameScore(
    user.id,
    score,
    message.chat.id,
    message.messageId,
    force,
    disableEditMessage,
)
