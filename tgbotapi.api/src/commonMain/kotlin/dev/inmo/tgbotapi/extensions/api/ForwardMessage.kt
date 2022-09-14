package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = execute(
    ForwardMessage(fromChatId, toChatId, messageId, disableNotification, protectContent)
)

suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(fromChat.id, toChatId, messageId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(fromChatId, toChat.id, messageId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(fromChat.id, toChat.id, messageId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    toChatId: ChatIdentifier,
    message: Message,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(message.chat, toChatId, message.messageId, disableNotification, protectContent)

suspend fun TelegramBot.forwardMessage(
    toChat: Chat,
    message: Message,
    disableNotification: Boolean = false,
    protectContent: Boolean = false
) = forwardMessage(message.chat, toChat, message.messageId, disableNotification, protectContent)
