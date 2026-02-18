package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetUserProfileAudios
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.UserProfileAudios

public suspend fun TelegramBot.getUserProfileAudios(
    userId: UserId,
    offset: Int? = null,
    limit: Int? = null
): UserProfileAudios = execute(
    GetUserProfileAudios(
        userId = userId, offset = offset, limit = limit
    )
)

public suspend fun TelegramBot.getUserProfileAudios(
    user: CommonUser,
    offset: Int? = null,
    limit: Int? = null
): UserProfileAudios = getUserProfileAudios(userId = user.id, offset = offset, limit = limit)
