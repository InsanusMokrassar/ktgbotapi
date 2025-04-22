// THIS CODE HAVE BEEN GENERATED AUTOMATICALLY
// TO REGENERATE IT JUST DELETE FILE
// ORIGINAL FILE: SendGift.kt
package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.message.ParseMode
import kotlin.Boolean
import kotlin.String

public suspend fun TelegramBot.sendGiftToChat(
    chat: PublicChat,
    giftId: GiftId,
    text: String,
    parseMode: ParseMode?,
): Boolean =
    sendGiftToChat(
        chatId = with(chat) { id },
        giftId = giftId,
        text = text,
        parseMode = parseMode,
    )

public suspend fun TelegramBot.sendGiftToChat(
    chat: PublicChat,
    giftId: GiftId,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean,
): Boolean =
    sendGiftToChat(
        chatId = with(chat) { id },
        giftId = giftId,
        text = text,
        parseMode = parseMode,
        upgradableToUnique = upgradableToUnique,
    )

public suspend fun TelegramBot.sendGiftToChat(
    chatId: ChatIdentifier,
    gift: Gift.Regular,
    text: String,
    parseMode: ParseMode?,
): Boolean =
    sendGiftToChat(
        chatId = chatId,
        giftId = with(gift) { id },
        text = text,
        parseMode = parseMode,
    )

public suspend fun TelegramBot.sendGiftToChat(
    chatId: ChatIdentifier,
    gift: Gift.Regular,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean,
): Boolean =
    sendGiftToChat(
        chatId = chatId,
        giftId = with(gift) { id },
        text = text,
        parseMode = parseMode,
        upgradableToUnique = upgradableToUnique,
    )

public suspend fun TelegramBot.sendGiftToChat(
    chat: PublicChat,
    gift: Gift.Regular,
    text: String,
    parseMode: ParseMode?,
): Boolean =
    sendGiftToChat(
        chat = chat,
        giftId = with(gift) { id },
        text = text,
        parseMode = parseMode,
    )

public suspend fun TelegramBot.sendGiftToChat(
    chat: PublicChat,
    gift: Gift.Regular,
    text: String,
    parseMode: ParseMode?,
    upgradableToUnique: Boolean,
): Boolean =
    sendGiftToChat(
        chat = chat,
        giftId = with(gift) { id },
        text = text,
        parseMode = parseMode,
        upgradableToUnique =
        upgradableToUnique,
    )
