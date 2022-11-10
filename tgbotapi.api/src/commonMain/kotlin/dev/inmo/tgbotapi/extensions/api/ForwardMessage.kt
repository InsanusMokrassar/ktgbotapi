package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.threadId

suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = execute(
    ForwardMessage(fromChatId, toChatId, messageId, threadId, disableNotification, protectContent)
)

suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(fromChat.id, toChatId, messageId, threadId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageId,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(fromChatId, toChat.id, messageId, threadId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageId,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(fromChat.id, toChat.id, messageId, threadId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    toChatId: ChatIdentifier,
    message: Message,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(message.chat, toChatId, message.messageId, threadId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    toChat: Chat,
    message: Message,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(message.chat, toChat, message.messageId, threadId, disableNotification, protectContent)
