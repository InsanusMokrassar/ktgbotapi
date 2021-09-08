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


/**
 * Displays nice "typing" text in chat header
 */
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


/**
 * Displays some nice "typing" text in chat header
 */
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

