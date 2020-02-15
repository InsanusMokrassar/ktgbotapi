package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendDocument
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.DocumentFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize

suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: FileId,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        text,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    thumb: PhotoSize? = document.thumb,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDocument(
    chatId, document.fileId, thumb ?.fileId, text, parseMode, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: MultipartFile,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        text,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: MultipartFile,
    thumb: MultipartFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        text,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: FileId,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendDocument(
        chatId,
        document,
        thumb,
        text,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: MultipartFile,
    thumb: PhotoSize? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDocument(
    chatId, document, thumb ?.fileId , text, parseMode, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDocument(
    chatId, document.fileId, thumb, text, parseMode, disableNotification, replyToMessageId, replyMarkup
)
