package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.games

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.games.SendGame
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.games.Game

suspend fun TelegramBot.sendGame(
    chatId: ChatIdentifier,
    gameShortName: String,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendGame(
        chatId, gameShortName, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun TelegramBot.sendGame(
    chat: Chat,
    gameShortName: String,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chat.id, gameShortName, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.sendGame(
    chatId: ChatIdentifier,
    game: Game,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chatId, game.title, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.sendGame(
    chat: Chat,
    game: Game,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    chat.id, game.title, disableNotification, replyToMessageId, replyMarkup
)
