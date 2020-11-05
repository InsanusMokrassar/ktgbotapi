package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.CopyMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    CopyMessage(fromChatId, toChatId, messageId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
)

suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, toChatId, messageId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChatId, toChat.id, messageId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, toChat.id, messageId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    message: Message,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, toChatId, message.messageId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    message: Message,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, toChat, message.messageId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    CopyMessage(fromChatId, toChatId, messageId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
)

suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, toChatId, messageId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageIdentifier,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChatId, toChat.id, messageId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageIdentifier,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, toChat.id, messageId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    message: Message,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, toChatId, message.messageId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    message: Message,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, toChat, message.messageId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
