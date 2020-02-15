package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendVideo
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VideoFile

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: FileId,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideo(
        chatId,
        video,
        thumb,
        text,
        parseMode,
        duration,
        width,
        height,
        null,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    thumb: PhotoSize? = video.thumb,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId, video.fileId, thumb ?.fileId, text, parseMode, video.duration, video.width, video.height, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: MultipartFile,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideo(chatId, video, thumb, text, parseMode, duration, width, height, supportStreaming, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: MultipartFile,
    thumb: MultipartFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideo(chatId, video, thumb, text, parseMode, duration, width, height, supportStreaming, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: FileId,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideo(chatId, video, thumb, text, parseMode, duration, width, height, null, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: MultipartFile,
    thumb: PhotoSize? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportStreaming: Boolean? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId, video, thumb ?.fileId , text, parseMode, duration, width, height, supportStreaming, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId, video.fileId, thumb, text, parseMode, video.duration, video.width, video.height, disableNotification, replyToMessageId, replyMarkup
)

