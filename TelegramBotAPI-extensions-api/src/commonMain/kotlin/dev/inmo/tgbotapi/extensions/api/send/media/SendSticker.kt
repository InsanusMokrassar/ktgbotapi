package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendSticker
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendSticker(
    chatId: ChatIdentifier,
    sticker: InputFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendSticker(chatId, sticker, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: InputFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat.id, sticker, disableNotification, replyToMessageId, replyMarkup)

suspend fun TelegramBot.sendSticker(
    chatId: ChatIdentifier,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chatId, sticker.fileId, disableNotification, replyToMessageId, replyMarkup)

suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat, sticker.fileId, disableNotification, replyToMessageId, replyMarkup)

suspend inline fun TelegramBot.replyWithSticker(
    to: Message,
    sticker: InputFile,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(to.chat, sticker, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.replyWithSticker(
    to: Message,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(to.chat, sticker, disableNotification, to.messageId, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = replyWithSticker(to, sticker, disableNotification, replyMarkup)
