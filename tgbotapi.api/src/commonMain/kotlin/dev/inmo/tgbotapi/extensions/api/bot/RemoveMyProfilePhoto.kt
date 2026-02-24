package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.RemoveMyProfilePhoto

public suspend fun TelegramBot.removeMyProfilePhoto(): Boolean = execute(RemoveMyProfilePhoto)
