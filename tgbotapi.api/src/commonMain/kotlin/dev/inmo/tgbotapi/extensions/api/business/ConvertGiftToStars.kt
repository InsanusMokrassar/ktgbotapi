package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.micro_utils.ksp.variations.GenerateVariations
import dev.inmo.micro_utils.ksp.variations.GenerationVariant
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.ConvertGiftToStars
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.gifts.Gift

public suspend fun TelegramBot.convertGiftToStars(
    businessConnectionId: BusinessConnectionId,
    ownedGiftId: GiftId
): Boolean = execute(
    ConvertGiftToStars(businessConnectionId, ownedGiftId)
)

public suspend fun TelegramBot.convertGiftToStars(
    businessConnectionId: BusinessConnectionId,
    gift: Gift.Regular
): Boolean = convertGiftToStars(
    businessConnectionId = businessConnectionId,
    ownedGiftId = with(gift) {id}
)

