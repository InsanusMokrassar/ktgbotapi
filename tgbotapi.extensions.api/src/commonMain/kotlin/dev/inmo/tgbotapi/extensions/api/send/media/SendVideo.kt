package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.replyWithVideo
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendVideo
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
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

suspend inline fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
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
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chatId, video.fileId, video.thumb ?.fileId, entities, video.duration, video.width, video.height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.sendVideo(
    chat: Chat,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
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
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chat.id, video, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


@Deprecated(
    "Replaced",
    ReplaceWith("replyWithVideo", "dev.inmo.tgbotapi.extensions.api.send.replyWithVideo")
)
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
) = replyWithVideo(to, video, thumb, text, parseMode, duration, width, height, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, video, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, video, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("replyWithVideo", "dev.inmo.tgbotapi.extensions.api.send.replyWithVideo")
)
suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithVideo(to, video, thumb, entities, duration, width, height, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: VideoFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, video, entities, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    video: VideoFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, video, entities, disableNotification, allowSendingWithoutReply, replyMarkup)
