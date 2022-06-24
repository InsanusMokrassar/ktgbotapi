package dev.inmo.tgbotapi.extensions.api.edit.common

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.media.editMessageMedia
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MediaContent

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(chatId, messageId, media, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageIdentifier,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(chat, messageId, media, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<MediaContent>,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(message, media, replyMarkup)
