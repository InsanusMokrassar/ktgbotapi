package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetBusinessConnection
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId

public suspend fun TelegramBot.getBusinessConnection(id: BusinessConnectionId): BusinessConnection =
    execute(GetBusinessConnection(businessConnectionId = id))

@Warning("This method may lead to error due to raw String type usage")
public suspend fun TelegramBot.getBusinessConnection(id: String): BusinessConnection =
    getBusinessConnection(
        BusinessConnectionId(id),
    )
