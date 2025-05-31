package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.RemoveBusinessAccountProfilePhoto
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

public suspend fun TelegramBot.removeBusinessAccountProfilePhoto(
    businessConnectionId: BusinessConnectionId,
    isPublic: Boolean = false
): Boolean = execute(
    RemoveBusinessAccountProfilePhoto(businessConnectionId = businessConnectionId, isPublic = isPublic)
) 