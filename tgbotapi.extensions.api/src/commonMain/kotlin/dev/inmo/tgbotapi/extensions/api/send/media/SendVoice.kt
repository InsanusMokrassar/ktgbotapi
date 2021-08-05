package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.replyWithVoice
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendVoice
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
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
    allowSendingWithoutReply: Boolean? = null,
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
        allowSendingWithoutReply,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat.id, voice, text, parseMode, duration, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.sendVoice(
    chatId: ChatIdentifier,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(
    chatId, voice.fileId, text, parseMode, voice.duration, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend fun TelegramBot.sendVoice(
    chat: Chat,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat.id, voice, text, parseMode, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.sendVoice(
    chatId: ChatIdentifier,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVoice(
        chatId,
        voice,
        entities,
        duration,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

suspend inline fun TelegramBot.sendVoice(
    chat: Chat,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat.id, voice, entities, duration, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.sendVoice(
    chatId: ChatIdentifier,
    voice: VoiceFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(
    chatId, voice.fileId, entities, voice.duration, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

suspend inline fun TelegramBot.sendVoice(
    chat: Chat,
    voice: VoiceFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat.id, voice, entities, disableNotification, replyToMessageId, allowSendingWithoutReply, replyMarkup)


@Deprecated(
    "Replaced",
    ReplaceWith("replyWithVoice", "dev.inmo.tgbotapi.extensions.api.send.replyWithVoice")
)
suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithVoice(to, voice, text, parseMode, duration, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, voice, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, voice, text, parseMode, disableNotification, allowSendingWithoutReply, replyMarkup)


@Deprecated(
    "Replaced",
    ReplaceWith("replyWithVoice", "dev.inmo.tgbotapi.extensions.api.send.replyWithVoice")
)
suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithVoice(to, voice, entities, duration, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: VoiceFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, voice, entities, disableNotification, allowSendingWithoutReply, replyMarkup)

@Deprecated(
    "Replaced",
    ReplaceWith("reply", "dev.inmo.tgbotapi.extensions.api.send.reply")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    voice: VoiceFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, voice, entities, disableNotification, allowSendingWithoutReply, replyMarkup)
