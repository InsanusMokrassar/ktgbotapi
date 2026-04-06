package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.SavePreparedKeyboardButton
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.buttons.KeyboardButton
import dev.inmo.tgbotapi.types.buttons.PreparedKeyboardButton
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.toChatId

public suspend fun TelegramBot.savePreparedKeyboardButton(
    userId: ChatId,
    button: KeyboardButton
): PreparedKeyboardButton = execute(
    SavePreparedKeyboardButton(userId = userId, button = button)
)

public suspend fun TelegramBot.savePreparedKeyboardButton(
    user: User,
    button: KeyboardButton
): PreparedKeyboardButton = savePreparedKeyboardButton(userId = user.id.toChatId(), button = button)
