package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMyDefaultAdministratorRights
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights

public suspend fun TelegramBot.setMyDefaultAdministratorRights(
    rights: ChatCommonAdministratorRights,
    forChannels: Boolean? = null
): Unit = execute(SetMyDefaultAdministratorRights(rights, forChannels))

public suspend fun TelegramBot.setMyDefaultAdministratorRightsForChannels(
    rights: ChatCommonAdministratorRights
): Unit = setMyDefaultAdministratorRights(rights, forChannels = true)

public suspend fun TelegramBot.setMyDefaultAdministratorRightsForGroupsAndSupergroups(
    rights: ChatCommonAdministratorRights
): Unit = setMyDefaultAdministratorRights(rights, forChannels = false)
