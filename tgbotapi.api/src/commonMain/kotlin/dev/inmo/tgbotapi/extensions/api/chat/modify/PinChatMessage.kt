package dev.inmo.tgbotapi.extensions.api.chat.modify

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.modify.PinChatMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.businessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

public suspend fun TelegramBot.pinChatMessage(
    chatId: ChatIdentifier,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false
): Boolean = execute(PinChatMessage(chatId, messageId, businessConnectionId, disableNotification))

public suspend fun TelegramBot.pinChatMessage(
    chat: Chat,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false
): Boolean = pinChatMessage(chat.id, messageId, businessConnectionId, disableNotification)

public suspend fun TelegramBot.pinChatMessage(
    message: AccessibleMessage,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    disableNotification: Boolean = false
): Boolean = pinChatMessage(message.chat.id, message.messageId, businessConnectionId, disableNotification)
