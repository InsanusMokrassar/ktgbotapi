package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendAction
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.businessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.threadId

suspend fun TelegramBot.sendBotAction(
    chatId: ChatIdentifier,
    action: BotAction,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = execute(
    SendAction(chatId, action, threadId, businessConnectionId)
)

suspend fun TelegramBot.sendBotAction(
    chat: Chat,
    action: BotAction,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat.id, action, threadId, businessConnectionId)


suspend fun TelegramBot.sendActionTyping(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, TypingAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadPhoto(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, UploadPhotoAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionRecordVideo(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, RecordVideoAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadVideo(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, UploadVideoAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionRecordVoice(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, RecordVoiceAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadVoice(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, UploadVoiceAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadDocument(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, UploadDocumentAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionFindLocation(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, FindLocationAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionRecordVideoNote(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, RecordVideoNoteAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadVideoNote(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
) = sendBotAction(chatId, UploadVideoNoteAction, threadId, businessConnectionId)


suspend fun TelegramBot.sendActionTyping(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, TypingAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadPhoto(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, UploadPhotoAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionRecordVideo(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, RecordVideoAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadVideo(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, UploadVideoAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionRecordVoice(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, RecordVoiceAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadVoice(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, UploadVoiceAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadDocument(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, UploadDocumentAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionFindLocation(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, FindLocationAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionRecordVideoNote(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, RecordVideoNoteAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionUploadVideoNote(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, UploadVideoNoteAction, threadId, businessConnectionId)

suspend fun TelegramBot.sendActionChooseStickerAction(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
) = sendBotAction(chat, ChooseStickerAction, threadId, businessConnectionId)

