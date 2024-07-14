package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetUserProfilePhotos
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.UserProfilePhotos

public suspend fun TelegramBot.getUserProfilePhotos(
    userId: UserId,
    offset: Int? = null,
    limit: Int? = null
): UserProfilePhotos = execute(
    GetUserProfilePhotos(
        userId, offset, limit
    )
)

public suspend fun TelegramBot.getUserProfilePhotos(
    user: CommonUser,
    offset: Int? = null,
    limit: Int? = null
): UserProfilePhotos = getUserProfilePhotos(user.id, offset, limit)
