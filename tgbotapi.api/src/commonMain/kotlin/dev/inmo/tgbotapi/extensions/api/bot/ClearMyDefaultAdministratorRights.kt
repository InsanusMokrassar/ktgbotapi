package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.ClearMyDefaultAdministratorRights
import dev.inmo.tgbotapi.types.ChatAdministratorRightsImpl

suspend fun TelegramBot.clearMyDefaultAdministratorRights(
    forChannels: Boolean? = null
) = execute(ClearMyDefaultAdministratorRights(forChannels))

suspend fun TelegramBot.clearMyDefaultAdministratorRightsForChannels() = clearMyDefaultAdministratorRights(forChannels = true)

suspend fun TelegramBot.clearMyDefaultAdministratorRightsForGroupsAndSupergroups() = clearMyDefaultAdministratorRights(forChannels = false)
