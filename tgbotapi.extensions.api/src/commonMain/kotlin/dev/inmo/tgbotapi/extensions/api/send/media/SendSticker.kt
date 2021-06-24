package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.replyWithSticker
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendSticker(chatId, sticker, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)
)

suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: InputFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat.id, sticker, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendSticker(
    chatId: ChatIdentifier,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chatId, sticker.fileId, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: Sticker,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat, sticker.fileId, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


@Deprecated(
    "Replaced",
    ReplaceWith("replyWithSticker", "dev.inmo.tgbotapi.extensions.api.send.replyWithSticker")
)
suspend inline fun TelegramBot.replyWithSticker(
    to: Message,
    sticker: InputFile,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithSticker(to, sticker, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.replyWithSticker(
    to: Message,
    sticker: Sticker,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, sticker, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    sticker: Sticker,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, sticker, disableNotification, allowSendingWithoutReply, replyMarkup)
