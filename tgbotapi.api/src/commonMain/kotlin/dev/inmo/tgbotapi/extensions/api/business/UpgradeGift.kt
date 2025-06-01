package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.micro_utils.ksp.variations.GenerateVariations
import dev.inmo.micro_utils.ksp.variations.GenerationVariant
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.ConvertGiftToStars
import dev.inmo.tgbotapi.requests.business_connection.UpgradeGift
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.keepOriginalDetailsField
import dev.inmo.tgbotapi.types.ownedGiftIdField
import dev.inmo.tgbotapi.types.starCountField
import kotlinx.serialization.SerialName

public suspend fun TelegramBot.convertGiftToStars(
    businessConnectionId: BusinessConnectionId,
    ownedGiftId: GiftId,
    keepOriginalDetails: Boolean = false,
    starCount: Int? = null
): Boolean = execute(
    UpgradeGift(
        businessConnectionId = businessConnectionId,
        ownedGiftId = ownedGiftId,
        keepOriginalDetails = keepOriginalDetails,
        starCount = starCount
    )
)

public suspend fun TelegramBot.convertGiftToStars(
    businessConnectionId: BusinessConnectionId,
    gift: Gift.Regular,
    keepOriginalDetails: Boolean = false,
): Boolean = execute(
    UpgradeGift(
        businessConnectionId = businessConnectionId,
        ownedGiftId = gift.id,
        keepOriginalDetails = keepOriginalDetails,
        starCount = gift.upgradeStarCount
    )
)
