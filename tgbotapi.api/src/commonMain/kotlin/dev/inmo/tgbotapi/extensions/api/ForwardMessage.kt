package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage
import dev.inmo.tgbotapi.types.threadId

public suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    threadId: MessageThreadId? = toChatId.threadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
): PossiblyForwardedMessage = execute(
    ForwardMessage(fromChatId, toChatId, messageId, threadId, startTimestamp, disableNotification, protectContent)
)

public suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    threadId: MessageThreadId? = toChatId.threadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
): PossiblyForwardedMessage = forwardMessage(fromChat.id, toChatId, messageId, threadId, startTimestamp, disableNotification, protectContent)

public suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageId,
    threadId: MessageThreadId? = toChat.id.threadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
): PossiblyForwardedMessage = forwardMessage(fromChatId, toChat.id, messageId, threadId, startTimestamp, disableNotification, protectContent)

public suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageId,
    threadId: MessageThreadId? = toChat.id.threadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
): PossiblyForwardedMessage = forwardMessage(fromChat.id, toChat.id, messageId, threadId, startTimestamp, disableNotification, protectContent)

public suspend fun TelegramBot.forwardMessage(
    toChatId: ChatIdentifier,
    message: AccessibleMessage,
    threadId: MessageThreadId? = toChatId.threadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
): PossiblyForwardedMessage = forwardMessage(message.chat, toChatId, message.messageId, threadId, startTimestamp, disableNotification, protectContent)

public suspend fun TelegramBot.forwardMessage(
    toChat: Chat,
    message: AccessibleMessage,
    threadId: MessageThreadId? = toChat.id.threadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
): PossiblyForwardedMessage = forwardMessage(message.chat, toChat, message.messageId, threadId, startTimestamp, disableNotification, protectContent)
