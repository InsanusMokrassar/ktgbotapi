package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.GiftPremiumSubscription
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

public suspend fun TelegramBot.giftPremiumSubscription(
    userId: UserId,
    monthCount: Int,
    starCount: Int,
    text: String,
    parseMode: ParseMode? = null
): Boolean = execute(
    GiftPremiumSubscription(
        userId = userId,
        monthCount = monthCount,
        starCount = starCount,
        text = text,
        parseMode = parseMode
    )
)

public suspend fun TelegramBot.giftPremiumSubscription(
    userId: UserId,
    monthCount: Int,
    starCount: Int,
    textSources: TextSourcesList? = null,
): Boolean = execute(
    GiftPremiumSubscription(
        userId = userId,
        monthCount = monthCount,
        starCount = starCount,
        textSources = textSources
    )
)

public suspend fun TelegramBot.giftPremiumSubscription(
    userId: UserId,
    monthCount: Int,
    starCount: Int,
    separator: TextSource? = null,
    textBuilder: EntitiesBuilderBody
): Boolean = execute(
    GiftPremiumSubscription(
        userId = userId,
        monthCount = monthCount,
        starCount = starCount,
        textSources = buildEntities(separator, textBuilder),
    )
)
