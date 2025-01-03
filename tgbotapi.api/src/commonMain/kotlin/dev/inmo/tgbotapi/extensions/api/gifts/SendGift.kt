package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.SendGift
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList

public suspend fun TelegramBot.sendGift(
    userId: UserId,
    giftId: GiftId,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean = false
): Boolean = execute(
    SendGift(
        userId = userId,
        giftId = giftId,
        text = text,
        parseMode = parseMode,
        upgradableToUnique = upgradableToUnique
    )
)

public suspend fun TelegramBot.sendGift(
    userId: UserId,
    giftId: GiftId,
    textSources: TextSourcesList,
    upgradableToUnique: Boolean = false,
): Boolean = execute(
    SendGift(
        userId = userId,
        giftId = giftId,
        textSources = textSources,
        upgradableToUnique = upgradableToUnique
    )
)

public suspend fun TelegramBot.sendGift(
    user: User,
    giftId: GiftId,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean = false
): Boolean = sendGift(
    userId = user.id,
    giftId = giftId,
    text = text,
    parseMode = parseMode,
    upgradableToUnique = upgradableToUnique
)

public suspend fun TelegramBot.sendGift(
    user: User,
    giftId: GiftId,
    textSources: TextSourcesList,
    upgradableToUnique: Boolean = false,
): Boolean = sendGift(
    userId = user.id,
    giftId = giftId,
    textSources = textSources,
    upgradableToUnique = upgradableToUnique
)

public suspend fun TelegramBot.sendGift(
    user: UserId,
    gift: Gift,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean = false
): Boolean = sendGift(
    userId = user,
    giftId = gift.id,
    text = text,
    parseMode = parseMode,
    upgradableToUnique = upgradableToUnique
)

public suspend fun TelegramBot.sendGift(
    user: UserId,
    gift: Gift,
    textSources: TextSourcesList,
    upgradableToUnique: Boolean = false,
): Boolean = sendGift(
    userId = user,
    giftId = gift.id,
    textSources = textSources,
    upgradableToUnique = upgradableToUnique
)


public suspend fun TelegramBot.sendGift(
    user: User,
    gift: Gift,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean = false
): Boolean = sendGift(
    userId = user.id,
    giftId = gift.id,
    text = text,
    parseMode = parseMode,
    upgradableToUnique = upgradableToUnique
)

public suspend fun TelegramBot.sendGift(
    user: User,
    gift: Gift,
    textSources: TextSourcesList,
    upgradableToUnique: Boolean = false,
): Boolean = sendGift(
    userId = user.id,
    giftId = gift.id,
    textSources = textSources,
    upgradableToUnique = upgradableToUnique
)
