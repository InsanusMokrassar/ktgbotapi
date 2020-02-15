package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.get.GetUserProfilePhotos
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId

suspend fun RequestsExecutor.getUserProfilePhotos(
    userId: UserId,
    offset: Int? = null,
    limit: Int? = null
) = execute(
    GetUserProfilePhotos(
        userId, offset, limit
    )
)

suspend fun RequestsExecutor.getUserProfilePhotos(
    user: CommonUser,
    offset: Int? = null,
    limit: Int? = null
) = getUserProfilePhotos(user.id, offset, limit)
