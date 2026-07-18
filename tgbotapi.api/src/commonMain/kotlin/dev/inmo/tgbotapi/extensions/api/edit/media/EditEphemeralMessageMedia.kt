package dev.inmo.tgbotapi.extensions.api.edit.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.media.EditEphemeralMessageMedia
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageMedia(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    media: TelegramFreeMedia,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageMedia(chatId, receiverUserId, ephemeralMessageId, media, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageMedia(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    media: TelegramFreeMedia,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageMedia(chat.id, receiverUserId, ephemeralMessageId, media, replyMarkup)
