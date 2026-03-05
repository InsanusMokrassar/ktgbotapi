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

public suspend fun TelegramBot.sendBotAction(
    chatId: ChatIdentifier,
    action: BotAction,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = execute(
    SendAction(chatId, action, threadId, businessConnectionId)
)

public suspend fun TelegramBot.sendBotAction(
    chat: Chat,
    action: BotAction,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat.id, action, threadId, businessConnectionId)


public suspend fun TelegramBot.sendActionTyping(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, TypingAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadPhoto(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, UploadPhotoAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionRecordVideo(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, RecordVideoAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadVideo(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, UploadVideoAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionRecordVoice(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, RecordVoiceAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadVoice(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, UploadVoiceAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadDocument(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, UploadDocumentAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionFindLocation(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, FindLocationAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionRecordVideoNote(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, RecordVideoNoteAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadVideoNote(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId
): Unit = sendBotAction(chatId, UploadVideoNoteAction, threadId, businessConnectionId)


public suspend fun TelegramBot.sendActionTyping(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, TypingAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadPhoto(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, UploadPhotoAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionRecordVideo(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, RecordVideoAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadVideo(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, UploadVideoAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionRecordVoice(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, RecordVoiceAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadVoice(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, UploadVoiceAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadDocument(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, UploadDocumentAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionFindLocation(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, FindLocationAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionRecordVideoNote(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, RecordVideoNoteAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionUploadVideoNote(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, UploadVideoNoteAction, threadId, businessConnectionId)

public suspend fun TelegramBot.sendActionChooseStickerAction(
    chat: Chat,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId
): Unit = sendBotAction(chat, ChooseStickerAction, threadId, businessConnectionId)

