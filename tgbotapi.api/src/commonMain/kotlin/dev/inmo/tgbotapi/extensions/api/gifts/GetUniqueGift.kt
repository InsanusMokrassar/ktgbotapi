package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.GetUniqueGift
import dev.inmo.tgbotapi.types.gifts.Gift
import dev.inmo.tgbotapi.types.gifts.UniqueGiftName

public suspend fun TelegramBot.getUniqueGift(name: UniqueGiftName): Gift.Unique = execute(GetUniqueGift(name))
