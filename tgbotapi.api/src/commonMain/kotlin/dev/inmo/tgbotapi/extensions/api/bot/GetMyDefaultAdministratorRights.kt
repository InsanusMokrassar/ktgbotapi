package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyDefaultAdministratorRights

suspend fun TelegramBot.getMyDefaultAdministratorRights(
    forChannels: Boolean? = null
) = execute(GetMyDefaultAdministratorRights(forChannels))

suspend fun TelegramBot.getMyDefaultAdministratorRightsForChannels() = getMyDefaultAdministratorRights(forChannels = true)

suspend fun TelegramBot.getMyDefaultAdministratorRightsForGroupsAndSupergroups() = getMyDefaultAdministratorRights(forChannels = false)
