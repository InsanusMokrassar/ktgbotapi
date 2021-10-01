package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendVideoNote
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.VideoNoteFile

suspend fun TelegramBot.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideoNote(
        chatId,
        videoNote,
        thumb,
        duration,
        size,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend fun TelegramBot.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: VideoNoteFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(
    chatId, videoNote.fileId, videoNote.thumb ?.fileId, videoNote.duration, videoNote.width, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendVideoNote(
    chat: Chat,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(chat.id, videoNote, thumb, duration, size, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendVideoNote(
    chat: Chat,
    videoNote: VideoNoteFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(chat.id, videoNote, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
