package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.StopPoll
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.polls.Poll

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.stopPoll(
    chatId: ChatIdentifier,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null,
): Poll =
    execute(
        StopPoll(chatId, messageId, businessConnectionId, replyMarkup),
    )

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.stopPoll(
    chat: Chat,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null,
): Poll = stopPoll(chat.id, messageId, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.stopPoll(
    chatId: IdChatIdentifier,
    message: AccessibleMessage,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null,
): Poll = stopPoll(chatId, message.messageId, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.stopPoll(
    chat: Chat,
    message: AccessibleMessage,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null,
): Poll = stopPoll(chat.id, message.messageId, businessConnectionId, replyMarkup)
