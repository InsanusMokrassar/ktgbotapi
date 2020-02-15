package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendAudio
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.AudioFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize

suspend fun RequestsExecutor.sendAudio(
    chatId: ChatIdentifier,
    audio: FileId,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendAudio(
        chatId,
        audio,
        thumb,
        text,
        parseMode,
        duration,
        performer,
        title,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendAudio(
    chatId: ChatIdentifier,
    audio: AudioFile,
    thumb: PhotoSize? = audio.thumb,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(
    chatId, audio.fileId, thumb ?.fileId, text, parseMode, audio.duration, audio.performer, title, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendAudio(
    chatId: ChatIdentifier,
    audio: MultipartFile,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendAudio(
        chatId,
        audio,
        thumb,
        text,
        parseMode,
        duration,
        performer,
        title,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendAudio(
    chatId: ChatIdentifier,
    audio: MultipartFile,
    thumb: MultipartFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendAudio(
        chatId,
        audio,
        thumb,
        text,
        parseMode,
        duration,
        performer,
        title,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendAudio(
    chatId: ChatIdentifier,
    audio: FileId,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendAudio(
        chatId,
        audio,
        thumb,
        text,
        parseMode,
        duration,
        performer,
        title,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendAudio(
    chatId: ChatIdentifier,
    audio: MultipartFile,
    thumb: PhotoSize? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(
    chatId, audio, thumb ?.fileId , text, parseMode, duration, performer, title, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendAudio(
    chatId: ChatIdentifier,
    audio: AudioFile,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(
    chatId, audio.fileId, thumb, text, parseMode, audio.duration, audio.performer, title, disableNotification, replyToMessageId, replyMarkup
)
