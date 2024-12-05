package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.GetAvailableGifts
import dev.inmo.tgbotapi.types.gifts.Gifts

public suspend fun TelegramBot.getAvailableGifts(): Gifts = execute(GetAvailableGifts)
