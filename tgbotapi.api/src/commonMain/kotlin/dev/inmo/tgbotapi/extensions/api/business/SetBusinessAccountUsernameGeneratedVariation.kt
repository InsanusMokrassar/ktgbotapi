// THIS CODE HAVE BEEN GENERATED AUTOMATICALLY
// TO REGENERATE IT JUST DELETE FILE
// ORIGINAL FILE: SetBusinessAccountUsername.kt
package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import kotlin.Boolean
import kotlin.String

public suspend fun TelegramBot.setBusinessAccountUsername(
    businessConnectionId: BusinessConnectionId,
    username: String,
): Boolean = setBusinessAccountUsername(
    businessConnectionId = businessConnectionId,
    username = with(username) { Username(username) },
)
