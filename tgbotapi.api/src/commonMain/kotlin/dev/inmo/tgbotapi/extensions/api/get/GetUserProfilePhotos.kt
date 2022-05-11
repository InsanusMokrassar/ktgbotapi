package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetUserProfilePhotos
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId

suspend fun TelegramBot.getUserProfilePhotos(
    userId: UserId,
    offset: Int? = null,
    limit: Int? = null
) = execute(
    GetUserProfilePhotos(
        userId, offset, limit
    )
)

suspend fun TelegramBot.getUserProfilePhotos(
    user: CommonUser,
    offset: Int? = null,
    limit: Int? = null
) = getUserProfilePhotos(user.id, offset, limit)
