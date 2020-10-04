package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendTextMessage(chatId, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(
    chatId, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.sendMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(chat.id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup)


suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat.id, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    to.chat,
    text,
    parseMode,
    disableWebPagePreview,
    disableNotification,
    to.messageId,
    replyMarkup
)
