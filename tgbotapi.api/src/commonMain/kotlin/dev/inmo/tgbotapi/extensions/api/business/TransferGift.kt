package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.micro_utils.ksp.variations.GenerateVariations
import dev.inmo.micro_utils.ksp.variations.GenerationVariant
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.ConvertGiftToStars
import dev.inmo.tgbotapi.requests.business_connection.TransferGift
import dev.inmo.tgbotapi.requests.business_connection.UpgradeGift
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.keepOriginalDetailsField
import dev.inmo.tgbotapi.types.ownedGiftIdField
import dev.inmo.tgbotapi.types.starCountField
import kotlinx.serialization.SerialName

public suspend fun TelegramBot.transferGift(
    businessConnectionId: BusinessConnectionId,
    ownedGiftId: GiftId,
    newOwnerChatId: ChatId,
    transferPaymentStarCount: Int? = null,
): Boolean = execute(
    TransferGift(
        businessConnectionId = businessConnectionId,
        ownedGiftId = ownedGiftId,
        newOwnerChatId = newOwnerChatId,
        transferPaymentStarCount = transferPaymentStarCount
    )
)

public suspend fun TelegramBot.transferGift(
    businessConnectionId: BusinessConnectionId,
    gift: Gift.Regular,
    newOwnerChatId: ChatId,
    transferPaymentStarCount: Int? = null,
): Boolean = transferGift(
    businessConnectionId = businessConnectionId,
    ownedGiftId = gift.id,
    newOwnerChatId = newOwnerChatId,
    transferPaymentStarCount = transferPaymentStarCount
)
