package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.micro_utils.ksp.variations.GenerateVariations
import dev.inmo.micro_utils.ksp.variations.GenerationVariant
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.ReadBusinessMessage
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.toChatId

@GenerateVariations
public suspend fun TelegramBot.readBusinessMessage(
    businessConnectionId: BusinessConnectionId,
    @GenerationVariant(Chat::class, "chat.id.toChatId()", "chat")
    chatId: ChatId,
    messageId: MessageId
): Boolean = execute(
    ReadBusinessMessage(businessConnectionId, chatId, messageId)
)

public suspend fun TelegramBot.readBusinessMessage(
    businessConnectionId: BusinessConnectionId,
    message: AccessibleMessage
): Boolean = readBusinessMessage(businessConnectionId, message.chat.id.toChatId(), message.messageId)