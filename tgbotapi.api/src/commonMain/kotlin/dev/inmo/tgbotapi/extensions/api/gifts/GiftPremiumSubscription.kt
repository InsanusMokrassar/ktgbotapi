package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.GiftPremiumSubscription
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList

public suspend fun TelegramBot.giftPremiumSubscription(
    userId: UserId,
    monthCount: Int,
    starCount: Int,
    text: String,
    parseMode: ParseMode? = null
): Boolean = execute(
    GiftPremiumSubscription(
        userId, monthCount, starCount, text, parseMode
    )
)

public suspend fun TelegramBot.giftPremiumSubscription(
    userId: UserId,
    monthCount: Int,
    starCount: Int,
    textSources: TextSourcesList? = null,
): Boolean = execute(
    GiftPremiumSubscription(
        userId, monthCount, starCount, textSources
    )
)
