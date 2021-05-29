package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendAudio
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
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
    allowSendingWithoutReply: Boolean? = null,
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
        allowSendingWithoutReply,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat.id, audio, thumb, text, parseMode, duration, performer, title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendAudio(
    chatId: ChatIdentifier,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chatId, audio.fileId, audio.thumb ?.fileId, text, parseMode, audio.duration, audio.performer, title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendAudio(
    chat: Chat,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat.id, audio, text, parseMode, title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, thumb, text, parseMode, duration, performer, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: Message,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, text, parseMode, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithAudio(to, audio, text, parseMode, title, disableNotification, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.sendAudio(
    chatId: ChatIdentifier,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendAudio(
        chatId,
        audio,
        thumb,
        entities,
        duration,
        performer,
        title,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend inline fun TelegramBot.sendAudio(
    chat: Chat,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat.id, audio, thumb, entities, duration, performer, title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.sendAudio(
    chatId: ChatIdentifier,
    audio: AudioFile,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chatId, audio.fileId, audio.thumb ?.fileId, entities, audio.duration, audio.performer, title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.sendAudio(
    chat: Chat,
    audio: AudioFile,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    title: String? = audio.title,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat.id, audio, entities, title, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: Message,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, thumb, entities, duration, performer, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: Message,
    audio: AudioFile,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, entities, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    audio: AudioFile,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithAudio(to, audio, entities, title, disableNotification, allowSendingWithoutReply, replyMarkup)
