package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.DeleteBusinessAccountGift
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.gifts.OwnedGift

public suspend fun TelegramBot.deleteBusinessAccountGift(
    businessConnectionId: BusinessConnectionId,
    ownedGiftId: GiftId,
): Unit = execute(DeleteBusinessAccountGift(businessConnectionId, ownedGiftId))

public suspend fun TelegramBot.deleteBusinessAccountGift(
    businessConnectionId: BusinessConnectionId,
    gift: OwnedGift.Regular.OwnedByBusinessAccount,
): Unit = deleteBusinessAccountGift(businessConnectionId, gift.ownedGiftId)
