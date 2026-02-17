package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyProfilePhoto
import dev.inmo.tgbotapi.requests.business_connection.InputProfilePhoto

public suspend fun TelegramBot.setMyProfilePhoto(
    photo: InputProfilePhoto
): Boolean = execute(SetMyProfilePhoto(photo))
