package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.micro_utils.ksp.variations.GenerateVariations
import dev.inmo.micro_utils.ksp.variations.GenerationVariant
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.SendGift
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList

@GenerateVariations
public suspend fun TelegramBot.sendGift(
    @GenerationVariant(
        User::class,
        "id",
        "user",
    )
    userId: UserId,
    @GenerationVariant(
        Gift.Regular::class,
        "id",
        "gift",
    )
    giftId: GiftId,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean = false,
): Boolean =
    execute(
        SendGift.toUser(
            userId = userId,
            giftId = giftId,
            text = text,
            parseMode = parseMode,
            upgradableToUnique = upgradableToUnique,
        ),
    )

@GenerateVariations
public suspend fun TelegramBot.sendGiftToChat(
    @GenerationVariant(
        PublicChat::class,
        "id",
        "chat",
    )
    chatId: ChatIdentifier,
    @GenerationVariant(
        Gift.Regular::class,
        "id",
        "gift",
    )
    giftId: GiftId,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean = false,
): Boolean =
    execute(
        SendGift.toChat(
            chatId = chatId,
            giftId = giftId,
            text = text,
            parseMode = parseMode,
            upgradableToUnique = upgradableToUnique,
        ),
    )

@GenerateVariations
public suspend fun TelegramBot.sendGift(
    @GenerationVariant(
        User::class,
        "id",
        "user",
    )
    userId: UserId,
    @GenerationVariant(
        Gift.Regular::class,
        "id",
        "gift",
    )
    giftId: GiftId,
    textSources: TextSourcesList,
    upgradableToUnique: Boolean = false,
): Boolean =
    execute(
        SendGift.toUser(
            userId = userId,
            giftId = giftId,
            textSources = textSources,
            upgradableToUnique = upgradableToUnique,
        ),
    )

@GenerateVariations
public suspend fun TelegramBot.sendGiftToChat(
    @GenerationVariant(
        PublicChat::class,
        "id",
        "chat",
    )
    chatId: ChatIdentifier,
    @GenerationVariant(
        Gift.Regular::class,
        "id",
        "gift",
    )
    giftId: GiftId,
    textSources: TextSourcesList,
    upgradableToUnique: Boolean = false,
): Boolean =
    execute(
        SendGift.toChat(
            chatId = chatId,
            giftId = giftId,
            textSources = textSources,
            upgradableToUnique = upgradableToUnique,
        ),
    )
