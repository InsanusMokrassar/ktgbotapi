package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendAction
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.chat.abstracts.Chat

suspend fun TelegramBot.sendBotAction(
    chatId: ChatIdentifier,
    action: BotAction
) = execute(
    SendAction(chatId, action)
)

suspend fun TelegramBot.sendBotAction(
    chat: Chat,
    action: BotAction
) = sendBotAction(chat.id, action)


suspend fun TelegramBot.sendActionTyping(
    chatId: ChatIdentifier
) = sendBotAction(chatId, TypingAction)

suspend fun TelegramBot.sendActionUploadPhoto(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadPhotoAction)

suspend fun TelegramBot.sendActionRecordVideo(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordVideoAction)

suspend fun TelegramBot.sendActionUploadVideo(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadVideoAction)

@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("sendActionRecordVoice", "dev.inmo.tgbotapi.extensions.api.send.sendActionRecordVoice")
)
suspend fun TelegramBot.sendActionRecordAudio(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordAudioAction)

@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("sendActionUploadVoice", "dev.inmo.tgbotapi.extensions.api.send.sendActionUploadVoice")
)
suspend fun TelegramBot.sendActionUploadAudio(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadAudioAction)

suspend fun TelegramBot.sendActionRecordVoice(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordVoiceAction)

suspend fun TelegramBot.sendActionUploadVoice(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadVoiceAction)

suspend fun TelegramBot.sendActionUploadDocument(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadDocumentAction)

suspend fun TelegramBot.sendActionFindLocation(
    chatId: ChatIdentifier
) = sendBotAction(chatId, FindLocationAction)

suspend fun TelegramBot.sendActionRecordVideoNote(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordVideoNoteAction)

suspend fun TelegramBot.sendActionUploadVideoNote(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadVideoNoteAction)


suspend fun TelegramBot.sendActionTyping(
    chat: Chat
) = sendBotAction(chat, TypingAction)

suspend fun TelegramBot.sendActionUploadPhoto(
    chat: Chat
) = sendBotAction(chat, UploadPhotoAction)

suspend fun TelegramBot.sendActionRecordVideo(
    chat: Chat
) = sendBotAction(chat, RecordVideoAction)

suspend fun TelegramBot.sendActionUploadVideo(
    chat: Chat
) = sendBotAction(chat, UploadVideoAction)

@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("sendActionRecordVoice", "dev.inmo.tgbotapi.extensions.api.send.sendActionRecordVoice")
)
suspend fun TelegramBot.sendActionRecordAudio(
    chat: Chat
) = sendBotAction(chat, RecordAudioAction)

@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("sendActionUploadVoice", "dev.inmo.tgbotapi.extensions.api.send.sendActionUploadVoice")
)
suspend fun TelegramBot.sendActionUploadAudio(
    chat: Chat
) = sendBotAction(chat, UploadAudioAction)

suspend fun TelegramBot.sendActionRecordVoice(
    chat: Chat
) = sendBotAction(chat, RecordVoiceAction)

suspend fun TelegramBot.sendActionUploadVoice(
    chat: Chat
) = sendBotAction(chat, UploadVoiceAction)

suspend fun TelegramBot.sendActionUploadDocument(
    chat: Chat
) = sendBotAction(chat, UploadDocumentAction)

suspend fun TelegramBot.sendActionFindLocation(
    chat: Chat
) = sendBotAction(chat, FindLocationAction)

suspend fun TelegramBot.sendActionRecordVideoNote(
    chat: Chat
) = sendBotAction(chat, RecordVideoNoteAction)

suspend fun TelegramBot.sendActionUploadVideoNote(
    chat: Chat
) = sendBotAction(chat, UploadVideoNoteAction)

