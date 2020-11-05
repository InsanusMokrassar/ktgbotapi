package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendVideo
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
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
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chatId, video.fileId, video.thumb ?.fileId, text, parseMode, video.duration, video.width, video.height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendVideo(
    chat: Chat,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chat.id, video, thumb, text, parseMode, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


suspend fun TelegramBot.sendVideo(
    chat: Chat,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chat.id, video, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, thumb, text, parseMode, duration, width, height, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithVideo(to, video, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    entities: List<TextSource>,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVideo(
        chatId,
        video,
        thumb,
        entities,
        duration,
        width,
        height,
        null,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend inline fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chatId, video.fileId, video.thumb ?.fileId, entities, video.duration, video.width, video.height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.sendVideo(
    chat: Chat,
    video: InputFile,
    thumb: InputFile? = null,
    entities: List<TextSource>,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chat.id, video, thumb, entities, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.sendVideo(
    chat: Chat,
    video: VideoFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chat.id, video, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: InputFile,
    thumb: InputFile? = null,
    entities: List<TextSource>,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, thumb, entities, duration, width, height, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: VideoFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    video: VideoFile,
    entities: List<TextSource>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithVideo(to, video, entities, disableNotification, allowSendingWithoutReply, replyMarkup)
