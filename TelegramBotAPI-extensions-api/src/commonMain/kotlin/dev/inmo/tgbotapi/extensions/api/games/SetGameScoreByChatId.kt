package dev.inmo.tgbotapi.extensions.api.games

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.games.SetGameScoreByChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.GameContent

suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    chatId: ChatId,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = execute(
    SetGameScoreByChatId(userId, score, chatId, messageId, force, disableEditMessage)
)

suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    chatId: ChatId,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    user.id, score, chatId, messageId, force, disableEditMessage
)

suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    chat: Chat,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    userId, score, chat.id, messageId, force, disableEditMessage
)

suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    chat: Chat,
    messageId: MessageIdentifier,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    user.id, score, chat.id, messageId, force, disableEditMessage
)

suspend fun TelegramBot.setGameScore(
    userId: UserId,
    score: Long,
    message: ContentMessage<GameContent>,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    userId, score, message.chat.id, message.messageId, force, disableEditMessage
)

suspend fun TelegramBot.setGameScore(
    user: CommonUser,
    score: Long,
    message: ContentMessage<GameContent>,
    force: Boolean = false,
    disableEditMessage: Boolean = false
) = setGameScore(
    user.id, score, message.chat.id, message.messageId, force, disableEditMessage
)
