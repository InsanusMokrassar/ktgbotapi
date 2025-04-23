package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.UnpinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.businessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

public suspend fun TelegramBot.unpinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageId? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
): Boolean = execute(UnpinChatMessage(chatId, messageId, businessConnectionId))

public suspend fun TelegramBot.unpinChatMessage(
    chat: Chat,
    messageId: MessageId? = null,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
): Boolean = unpinChatMessage(chat.id, messageId, businessConnectionId)

public suspend fun TelegramBot.unpinChatMessage(
    message: AccessibleMessage,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
): Boolean = unpinChatMessage(message.chat.id, message.messageId, businessConnectionId)
