package dev.inmo.tgbotapi.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.InputFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendSticker
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

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
