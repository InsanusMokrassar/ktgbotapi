package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendPhoto
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.Photo
import dev.inmo.tgbotapi.types.files.biggest
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendPhoto(
        chatId,
        fileId,
        text,
        parseMode,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat.id, fileId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chatId, photo.biggest() ?.fileId ?: error("Photo content must not be empty"), text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat.id, photo, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithPhoto(
    to: Message,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, fileId, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithPhoto(
    to: Message,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, photo, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithPhoto(to, photo, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendPhoto(
        chatId,
        fileId,
        entities,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat.id, fileId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photo: Photo,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chatId, photo.biggest() ?.fileId ?: error("Photo content must not be empty"), entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    photo: Photo,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat.id, photo, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithPhoto(
    to: Message,
    fileId: InputFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, fileId, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithPhoto(
    to: Message,
    photo: Photo,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, photo, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    photo: Photo,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithPhoto(to, photo, entities, disableNotification, allowSendingWithoutReply, replyMarkup)
