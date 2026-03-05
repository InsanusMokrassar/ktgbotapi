// THIS CODE HAVE BEEN GENERATED AUTOMATICALLY
// TO REGENERATE IT JUST DELETE FILE
// ORIGINAL FILE: SendGift.kt
package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.GiftId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.message.ParseMode
import kotlin.Boolean
import kotlin.String

public suspend fun TelegramBot.sendGift(
  user: User,
  giftId: GiftId,
  text: String,
  parseMode: ParseMode?,
): Unit = sendGift(
    userId = with(user) {id}, giftId = giftId, text = text, parseMode = parseMode
)

public suspend fun TelegramBot.sendGift(
  user: User,
  giftId: GiftId,
  text: String,
  parseMode: ParseMode?,
  upgradableToUnique: Boolean,
): Unit = sendGift(
    userId = with(user) {id}, giftId = giftId, text = text, parseMode = parseMode,
    upgradableToUnique = upgradableToUnique
)

public suspend fun TelegramBot.sendGift(
  userId: UserId,
  gift: Gift.Regular,
  text: String,
  parseMode: ParseMode?,
): Unit = sendGift(
    userId = userId, giftId = with(gift) {id}, text = text, parseMode = parseMode
)

public suspend fun TelegramBot.sendGift(
  userId: UserId,
  gift: Gift.Regular,
  text: String,
  parseMode: ParseMode?,
  upgradableToUnique: Boolean,
): Unit = sendGift(
    userId = userId, giftId = with(gift) {id}, text = text, parseMode = parseMode,
    upgradableToUnique = upgradableToUnique
)

public suspend fun TelegramBot.sendGift(
  user: User,
  gift: Gift.Regular,
  text: String,
  parseMode: ParseMode?,
): Unit = sendGift(
    user = user, giftId = with(gift) {id}, text = text, parseMode = parseMode
)

public suspend fun TelegramBot.sendGift(
  user: User,
  gift: Gift.Regular,
  text: String,
  parseMode: ParseMode?,
  upgradableToUnique: Boolean,
): Unit = sendGift(
    user = user, giftId = with(gift) {id}, text = text, parseMode = parseMode, upgradableToUnique =
    upgradableToUnique
)
