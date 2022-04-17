package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyDefaultAdministratorRights
import dev.inmo.tgbotapi.types.ChatAdministratorRightsImpl

suspend fun TelegramBot.setMyDefaultAdministratorRights(
    rights: ChatAdministratorRightsImpl,
    forChannels: Boolean? = null
) = execute(SetMyDefaultAdministratorRights(rights, forChannels))

suspend fun TelegramBot.setMyDefaultAdministratorRightsForChannels(
    rights: ChatAdministratorRightsImpl
) = setMyDefaultAdministratorRights(rights, forChannels = true)

suspend fun TelegramBot.setMyDefaultAdministratorRightsForGroupsAndSupergroups(
    rights: ChatAdministratorRightsImpl
) = setMyDefaultAdministratorRights(rights, forChannels = false)
