package dev.inmo.tgbotapi.extensions.api.send.games

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.replyWithGame
import dev.inmo.tgbotapi.requests.send.games.SendGame
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendGame(
    chatId: ChatIdentifier,
    gameShortName: String,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendGame(
        chatId, gameShortName, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
    )
)

suspend fun TelegramBot.sendGame(
    chat: Chat,
    gameShortName: String,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chat.id, gameShortName, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendGame(
    chatId: ChatIdentifier,
    game: Game,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chatId, game.title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendGame(
    chat: Chat,
    game: Game,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chat.id, game.title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)
