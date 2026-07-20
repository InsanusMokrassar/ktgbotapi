package dev.inmo.tgbotapi.extensions.api.edit.reply_markup

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.reply_markup.EditEphemeralMessageReplyMarkup
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageReplyMarkup(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageReplyMarkup(chatId, receiverUserId, ephemeralMessageId, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageReplyMarkup(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" },
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageReplyMarkup(chat.id, receiverUserId, ephemeralMessageId, replyMarkup)
