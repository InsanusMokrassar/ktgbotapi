package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendVoice
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.VoiceFile
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendVoice(
    chatId: ChatIdentifier,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVoice(
        chatId,
        voice,
        text,
        parseMode,
        duration,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun TelegramBot.sendVoice(
    chat: Chat,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat.id, voice, text, parseMode, duration, disableNotification, replyToMessageId, replyMarkup)

suspend fun TelegramBot.sendVoice(
    chatId: ChatIdentifier,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(
    chatId, voice.fileId, text, parseMode, voice.duration, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.sendVoice(
    chat: Chat,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat.id, voice, text, parseMode, disableNotification, replyToMessageId, replyMarkup)

suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(to.chat, voice, text, parseMode, duration, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(to.chat, voice, text, parseMode, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = replyWithVoice(to, voice, text, parseMode, disableNotification, replyMarkup)
