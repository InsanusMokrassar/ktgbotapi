package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.SetBusinessAccountGiftSettings
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.gifts.AcceptedGiftTypes
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage

/**
 * Sets the gift settings for a business account
 */
public suspend fun TelegramBot.setBusinessAccountGiftSettings(
    businessConnectionId: BusinessConnectionId,
    showGiftButton: Boolean,
    acceptedGiftTypes: AcceptedGiftTypes
): Boolean = execute(
    SetBusinessAccountGiftSettings(
        businessConnectionId = businessConnectionId,
        showGiftButton = showGiftButton,
        acceptedGiftTypes = acceptedGiftTypes
    )
)

/**
 * Sets the gift settings for a business account using business message context
 */
public suspend fun TelegramBot.setBusinessAccountGiftSettings(
    message: BusinessContentMessage<*>,
    showGiftButton: Boolean,
    acceptedGiftTypes: AcceptedGiftTypes
): Boolean = setBusinessAccountGiftSettings(
    businessConnectionId = message.businessConnectionId,
    showGiftButton = showGiftButton,
    acceptedGiftTypes = acceptedGiftTypes
) 