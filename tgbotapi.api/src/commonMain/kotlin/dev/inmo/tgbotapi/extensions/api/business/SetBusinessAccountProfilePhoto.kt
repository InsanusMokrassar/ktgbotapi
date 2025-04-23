package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.InputProfilePhoto
import dev.inmo.tgbotapi.requests.business_connection.SetBusinessAccountProfilePhoto
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

public suspend fun TelegramBot.setBusinessAccountProfilePhoto(
    businessConnectionId: BusinessConnectionId,
    photo: InputProfilePhoto,
    isPublic: Boolean = false,
): Boolean = execute(
    SetBusinessAccountProfilePhoto(businessConnectionId, photo, isPublic),
)
