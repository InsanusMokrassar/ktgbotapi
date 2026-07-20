package dev.inmo.tgbotapi.extensions.api.edit.caption

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.caption.EditEphemeralMessageCaption
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    caption: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageCaption(chatId, receiverUserId, ephemeralMessageId, caption, parseMode, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" },
    caption: String? = null,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageCaption(chat.id, receiverUserId, ephemeralMessageId, caption, parseMode, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageCaption(chatId, receiverUserId, ephemeralMessageId, entities, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" },
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageCaption(chat.id, receiverUserId, ephemeralMessageId, entities, replyMarkup)
