package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.micro_utils.ksp.variations.GenerateVariations
import dev.inmo.micro_utils.ksp.variations.GenerationVariant
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.SetBusinessAccountUsername
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

@GenerateVariations
public suspend fun TelegramBot.setBusinessAccountUsername(
    businessConnectionId: BusinessConnectionId,
    @GenerationVariant(String::class, "Username(username)", "username")
    username: Username,
): Boolean =
    execute(
        SetBusinessAccountUsername(businessConnectionId, username),
    ) 
