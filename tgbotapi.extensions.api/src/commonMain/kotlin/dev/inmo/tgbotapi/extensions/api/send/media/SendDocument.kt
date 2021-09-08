package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.replyWithDocument
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendDocument
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.message.abstracts.Message

/**
 * Sends a document message to a specified chat.
 * 
 * @param document - A document to send
 * @param thumb - A thumbnail to display in the chat
 * @param text - Text as a caption to the file
 * @param allowSendingWithoutReply - idk
 * @param replyMarkup - Visual buttons to display in the chat. Can be in the place of a keyboard or directly under the message
 * @param disableContentTypeDetection - idk
 */
suspend fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        text,
        parseMode,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup,
        disableContentTypeDetection
    )
)

/**
 * Sends a document message to a specified chat.
 * 
 * @param document - A document to send
 * @param thumb - A thumbnail to display in the chat
 * @param text - Text as a caption to the file
 * @param allowSendingWithoutReply - idk
 * @param replyMarkup - Visual buttons to display in the chat. Can be in the place of a keyboard or directly under the message
 * @param disableContentTypeDetection - idk
 */
suspend fun TelegramBot.sendDocument(
    chat: Chat,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, thumb, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

/**
 * Sends a document message to a specified chat.
 * 
 * @param document - A document to send
 * @param thumb - A thumbnail to display in the chat
 * @param text - Text as a caption to the file
 * @param allowSendingWithoutReply - idk
 * @param replyMarkup - Visual buttons to display in the chat. Can be in the place of a keyboard or directly under the message
 * @param disableContentTypeDetection - idk
 */
suspend fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(
    chatId, document.fileId, document.thumb ?.fileId, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection
)

/**
 * Sends a document message to a specified chat.
 * 
 * @param document - A document to send
 * @param thumb - A thumbnail to display in the chat
 * @param text - Text as a caption to the file
 * @param allowSendingWithoutReply - idk
 * @param replyMarkup - Visual buttons to display in the chat. Can be in the place of a keyboard or directly under the message
 * @param disableContentTypeDetection - idk
 */
suspend fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        entities,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup,
        disableContentTypeDetection
    )
)


suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, thumb, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(
    chatId, document.fileId, document.thumb ?.fileId, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection
)

suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat.id, document, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)


@Deprecated(
    "Replaced",
    ReplaceWith("replyWithDocument", "dev.inmo.tgbotapi.extensions.api.send.replyWithDocument")
)
suspend inline fun TelegramBot.replyWithDocument(
    to: Message,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = replyWithDocument(to, document, thumb, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.replyWithDocument(
    to: Message,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = reply(to, document, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = reply(to, document, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

@Deprecated(
    "Replaced",
    ReplaceWith("replyWithDocument", "dev.inmo.tgbotapi.extensions.api.send.replyWithDocument")
)
suspend inline fun TelegramBot.replyWithDocument(
    to: Message,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = replyWithDocument(to, document, thumb, entities, disableNotification, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.replyWithDocument(
    to: Message,
    document: DocumentFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = reply(to, document, entities, disableNotification, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    document: DocumentFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = reply(to, document, entities, disableNotification, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)
