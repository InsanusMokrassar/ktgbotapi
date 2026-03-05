package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.ClearMyDefaultAdministratorRights

public suspend fun TelegramBot.clearMyDefaultAdministratorRights(
    forChannels: Boolean? = null
): Unit = execute(ClearMyDefaultAdministratorRights(forChannels))

public suspend fun TelegramBot.clearMyDefaultAdministratorRightsForChannels(): Unit = clearMyDefaultAdministratorRights(forChannels = true)

public suspend fun TelegramBot.clearMyDefaultAdministratorRightsForGroupsAndSupergroups(): Unit = clearMyDefaultAdministratorRights(forChannels = false)
