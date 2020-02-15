package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendSticker
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker

suspend fun RequestsExecutor.sendSticker(
    chatId: ChatIdentifier,
    sticker: FileId,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendSticker(chatId, sticker, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun RequestsExecutor.sendSticker(
    chatId: ChatIdentifier,
    sticker: MultipartFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendSticker(chatId, sticker, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun RequestsExecutor.sendSticker(
    chat: Chat,
    sticker: FileId,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat.id, sticker, disableNotification, replyToMessageId, replyMarkup)

suspend fun RequestsExecutor.sendSticker(
    chat: Chat,
    sticker: MultipartFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat.id, sticker, disableNotification, replyToMessageId, replyMarkup)

suspend fun RequestsExecutor.sendSticker(
    chatId: ChatIdentifier,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(
    chatId, sticker.fileId, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendSticker(
    chat: Chat,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat.id, sticker.fileId, disableNotification, replyToMessageId, replyMarkup)

