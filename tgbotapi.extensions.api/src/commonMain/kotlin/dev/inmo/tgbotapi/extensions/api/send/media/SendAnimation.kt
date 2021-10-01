package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.replyWithAnimation
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendAnimation
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendAnimation(
    chatId: ChatIdentifier,
    animation: InputFile,
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
    SendAnimation(
        chatId,
        animation,
        thumb,
        text,
        parseMode,
        duration,
        width,
        height,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend fun TelegramBot.sendAnimation(
    chatId: ChatIdentifier,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    chatId, animation.fileId, animation.thumb ?.fileId, text, parseMode, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendAnimation(
    chat: Chat,
    animation: InputFile,
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
) = sendAnimation(chat.id, animation, thumb, text, parseMode, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendAnimation(
    chat: Chat,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(chat.id, animation, text, parseMode, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


suspend fun TelegramBot.sendAnimation(
    chatId: ChatIdentifier,
    animation: InputFile,
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
    SendAnimation(
        chatId,
        animation,
        thumb,
        entities,
        duration,
        width,
        height,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend fun TelegramBot.sendAnimation(
    chatId: ChatIdentifier,
    animation: AnimationFile,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    chatId, animation.fileId, animation.thumb ?.fileId, entities, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendAnimation(
    chat: Chat,
    animation: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(chat.id, animation, thumb, entities, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendAnimation(
    chat: Chat,
    animation: AnimationFile,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(chat.id, animation, entities, duration, width, height, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
