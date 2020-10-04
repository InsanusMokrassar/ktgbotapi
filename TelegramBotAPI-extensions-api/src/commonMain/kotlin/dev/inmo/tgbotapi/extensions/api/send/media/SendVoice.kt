package dev.inmo.tgbotapi.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.InputFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendVoice
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.files.AudioFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.VoiceFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

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
