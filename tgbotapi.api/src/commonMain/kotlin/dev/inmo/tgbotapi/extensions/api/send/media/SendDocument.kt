package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendDocument
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.files.DocumentFile

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        text,
        parseMode,
        threadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup,
        disableContentTypeDetection
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendDocument(
    chat: Chat,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, thumb, text, parseMode, threadId, disableNotification, protectContent, replyParameters, replyMarkup, disableContentTypeDetection)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(
    chatId, document.fileId, document.thumbnail ?.fileId, text, parseMode, threadId, disableNotification, protectContent, replyParameters, replyMarkup, disableContentTypeDetection
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, text, parseMode, threadId, disableNotification, protectContent, replyParameters, replyMarkup, disableContentTypeDetection)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        entities,
        threadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup,
        disableContentTypeDetection
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, thumb, entities, threadId, disableNotification, protectContent, replyParameters, replyMarkup, disableContentTypeDetection)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(
    chatId, document.fileId, document.thumbnail ?.fileId, entities, threadId, disableNotification, protectContent, replyParameters, replyMarkup, disableContentTypeDetection
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, entities, threadId, disableNotification, protectContent, replyParameters, replyMarkup, disableContentTypeDetection)
