package dev.inmo.tgbotapi.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.ForwardMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = execute(
    ForwardMessage(fromChatId, toChatId, messageId, disableNotification)
)

suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = forwardMessage(fromChat.id, toChatId, messageId, disableNotification)

suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = forwardMessage(fromChatId, toChat.id, messageId, disableNotification)

suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageIdentifier,
    disableNotification: Boolean = false
) = forwardMessage(fromChat.id, toChat.id, messageId, disableNotification)

suspend fun TelegramBot.forwardMessage(
    toChatId: ChatIdentifier,
    message: Message,
    disableNotification: Boolean = false
) = forwardMessage(message.chat, toChatId, message.messageId, disableNotification)

suspend fun TelegramBot.forwardMessage(
    toChat: Chat,
    message: Message,
    disableNotification: Boolean = false
) = forwardMessage(message.chat, toChat, message.messageId, disableNotification)
