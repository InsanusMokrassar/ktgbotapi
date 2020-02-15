package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.files.*

suspend fun RequestsExecutor.sendPhoto(
    chatId: ChatIdentifier,
    fileId: FileId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendPhoto(
        chatId,
        fileId,
        caption,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendPhoto(
    chatId: ChatIdentifier,
    photo: MultipartFile,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendPhoto(
        chatId,
        photo,
        caption,
        parseMode,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendPhoto(
    chatId: ChatIdentifier,
    file: PhotoSize,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId, file.fileId, caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendPhoto(
    chatId: ChatIdentifier,
    photo: Photo,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId, photo.biggest() ?: throw IllegalArgumentException("Photo $photo is empty"), caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendPhoto(
    chat: Chat,
    fileId: FileId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chat.id,
    fileId,
    caption,
    parseMode,
    disableNotification,
    replyToMessageId,
    replyMarkup
)

suspend fun RequestsExecutor.sendPhoto(
    chat: Chat,
    file: PhotoSize,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chat.id, file.fileId, caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendPhoto(
    chat: Chat,
    photo: Photo,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chat.id, photo.biggest() ?: throw IllegalArgumentException("Photo $photo is empty"), caption, parseMode, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendPhoto(
    chat: Chat,
    fileId: MultipartFile,
    caption: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chat.id,
    fileId,
    caption,
    parseMode,
    disableNotification,
    replyToMessageId,
    replyMarkup
)
