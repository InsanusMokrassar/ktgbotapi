package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendVideoNote
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.files.VideoNoteFile
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideoNote(
        chatId,
        videoNote,
        thumb,
        text,
        parseMode,
        duration,
        size,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun TelegramBot.sendVideoNote(
    chatId: ChatIdentifier,
    videoNote: VideoNoteFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(
    chatId, videoNote.fileId, videoNote.thumb ?.fileId, text, parseMode, videoNote.duration, videoNote.width, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.sendVideoNote(
    chat: Chat,
    videoNote: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(chat.id, videoNote, thumb, text, parseMode, duration, size, disableNotification, replyToMessageId, replyMarkup)

suspend fun TelegramBot.sendVideoNote(
    chat: Chat,
    videoNote: VideoNoteFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(chat.id, videoNote, text, parseMode, disableNotification, replyToMessageId, replyMarkup)

suspend inline fun TelegramBot.replyWithVideoNote(
    to: Message,
    videoNote: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(to.chat, videoNote, thumb, text, parseMode, duration, size, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.replyWithVideoNote(
    to: Message,
    videoNote: VideoNoteFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(to.chat, videoNote, text, parseMode, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    videoNote: VideoNoteFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = replyWithVideoNote(to, videoNote, text, parseMode, disableNotification, replyMarkup)
