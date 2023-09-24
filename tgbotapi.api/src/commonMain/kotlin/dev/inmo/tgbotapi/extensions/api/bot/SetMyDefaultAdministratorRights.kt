package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyDefaultAdministratorRights
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights

suspend fun TelegramBot.setMyDefaultAdministratorRights(
    rights: ChatCommonAdministratorRights,
    forChannels: Boolean? = null
) = execute(SetMyDefaultAdministratorRights(rights, forChannels))

suspend fun TelegramBot.setMyDefaultAdministratorRightsForChannels(
    rights: ChatCommonAdministratorRights
) = setMyDefaultAdministratorRights(rights, forChannels = true)

suspend fun TelegramBot.setMyDefaultAdministratorRightsForGroupsAndSupergroups(
    rights: ChatCommonAdministratorRights
) = setMyDefaultAdministratorRights(rights, forChannels = false)
