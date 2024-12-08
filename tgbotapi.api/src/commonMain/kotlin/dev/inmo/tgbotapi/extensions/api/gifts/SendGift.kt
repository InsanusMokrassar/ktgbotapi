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
    parseMode: ParseMode?
): Boolean = execute(
    SendGift(
        userId,
        giftId,
        text,
        parseMode
    )
)

public suspend fun TelegramBot.sendGift(
    userId: UserId,
    giftId: GiftId,
    textSources: TextSourcesList,
): Boolean = execute(
    SendGift(
        userId,
        giftId,
        textSources
    )
)

public suspend fun TelegramBot.sendGift(
    user: User,
    giftId: GiftId,
    text: String,
    parseMode: ParseMode?
): Boolean = sendGift(
    user.id,
    giftId,
    text,
    parseMode
)

public suspend fun TelegramBot.sendGift(
    user: User,
    giftId: GiftId,
    textSources: TextSourcesList,
): Boolean = sendGift(
    user.id,
    giftId,
    textSources
)

public suspend fun TelegramBot.sendGift(
    user: UserId,
    gift: Gift,
    text: String,
    parseMode: ParseMode?
): Boolean = sendGift(
    user,
    gift.id,
    text,
    parseMode
)

public suspend fun TelegramBot.sendGift(
    user: UserId,
    gift: Gift,
    textSources: TextSourcesList,
): Boolean = sendGift(
    user,
    gift.id,
    textSources
)


public suspend fun TelegramBot.sendGift(
    user: User,
    gift: Gift,
    text: String,
    parseMode: ParseMode?
): Boolean = sendGift(
    user.id,
    gift.id,
    text,
    parseMode
)

public suspend fun TelegramBot.sendGift(
    user: User,
    gift: Gift,
    textSources: TextSourcesList,
): Boolean = sendGift(
    user.id,
    gift.id,
    textSources
)
