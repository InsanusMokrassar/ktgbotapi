package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendAction
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.threadId

suspend fun TelegramBot.sendBotAction(
    chatId: ChatIdentifier,
    action: BotAction,
    threadId: MessageThreadId? = chatId.threadId
) = execute(
    SendAction(chatId, action, threadId)
)

suspend fun TelegramBot.sendBotAction(
    chat: Chat,
    action: BotAction,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat.id, action, threadId)


suspend fun TelegramBot.sendActionTyping(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, TypingAction, threadId)

suspend fun TelegramBot.sendActionUploadPhoto(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, UploadPhotoAction, threadId)

suspend fun TelegramBot.sendActionRecordVideo(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, RecordVideoAction, threadId)

suspend fun TelegramBot.sendActionUploadVideo(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, UploadVideoAction, threadId)

suspend fun TelegramBot.sendActionRecordVoice(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, RecordVoiceAction, threadId)

suspend fun TelegramBot.sendActionUploadVoice(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, UploadVoiceAction, threadId)

suspend fun TelegramBot.sendActionUploadDocument(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, UploadDocumentAction, threadId)

suspend fun TelegramBot.sendActionFindLocation(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, FindLocationAction, threadId)

suspend fun TelegramBot.sendActionRecordVideoNote(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, RecordVideoNoteAction, threadId)

suspend fun TelegramBot.sendActionUploadVideoNote(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId
) = sendBotAction(chatId, UploadVideoNoteAction, threadId)


suspend fun TelegramBot.sendActionTyping(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, TypingAction, threadId)

suspend fun TelegramBot.sendActionUploadPhoto(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, UploadPhotoAction, threadId)

suspend fun TelegramBot.sendActionRecordVideo(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, RecordVideoAction, threadId)

suspend fun TelegramBot.sendActionUploadVideo(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, UploadVideoAction, threadId)

suspend fun TelegramBot.sendActionRecordVoice(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, RecordVoiceAction, threadId)

suspend fun TelegramBot.sendActionUploadVoice(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, UploadVoiceAction, threadId)

suspend fun TelegramBot.sendActionUploadDocument(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, UploadDocumentAction, threadId)

suspend fun TelegramBot.sendActionFindLocation(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, FindLocationAction, threadId)

suspend fun TelegramBot.sendActionRecordVideoNote(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, RecordVideoNoteAction, threadId)

suspend fun TelegramBot.sendActionUploadVideoNote(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, UploadVideoNoteAction, threadId)

suspend fun TelegramBot.sendActionChooseStickerAction(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId
) = sendBotAction(chat, ChooseStickerAction, threadId)

