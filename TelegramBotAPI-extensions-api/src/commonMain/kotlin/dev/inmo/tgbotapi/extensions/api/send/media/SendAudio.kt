package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendAudio
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendAudio(
    chatId: ChatIdentifier,
    audio: InputFile,
    thumb: InputFile? = null,
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

suspend fun TelegramBot.sendAudio(
    chat: Chat,
    audio: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat.id, audio, thumb, text, parseMode, duration, performer, title, disableNotification, replyToMessageId, replyMarkup)

suspend fun TelegramBot.sendAudio(
    chatId: ChatIdentifier,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chatId, audio.fileId, audio.thumb ?.fileId, text, parseMode, audio.duration, audio.performer, title, disableNotification, replyToMessageId, replyMarkup)

suspend fun TelegramBot.sendAudio(
    chat: Chat,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat.id, audio, text, parseMode, title, disableNotification, replyToMessageId, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: Message,
    audio: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, thumb, text, parseMode, duration, performer, title, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: Message,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, text, parseMode, title, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = replyWithAudio(to, audio, text, parseMode, title, disableNotification, replyMarkup)
