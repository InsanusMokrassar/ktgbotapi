package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.micro_utils.ksp.variations.GenerateVariations
import dev.inmo.micro_utils.ksp.variations.GenerationVariant
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.DeleteBusinessMessages
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

@GenerateVariations
public suspend fun TelegramBot.deleteBusinessMessages(
    businessConnectionId: BusinessConnectionId,
    @GenerationVariant(List::class, "messages.map { it.messageId }", "messages", AccessibleMessage::class)
    messageIds: List<MessageId>,
): Boolean =
    execute(
        DeleteBusinessMessages(businessConnectionId, messageIds),
    )
