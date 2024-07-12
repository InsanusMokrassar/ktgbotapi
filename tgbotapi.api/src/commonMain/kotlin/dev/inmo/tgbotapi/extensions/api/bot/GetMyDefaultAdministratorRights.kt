package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.GetMyDefaultAdministratorRights
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember

public suspend fun TelegramBot.getMyDefaultAdministratorRights(
    forChannels: Boolean? = null
): AdministratorChatMember = execute(GetMyDefaultAdministratorRights(forChannels))

public suspend fun TelegramBot.getMyDefaultAdministratorRightsForChannels(): AdministratorChatMember = getMyDefaultAdministratorRights(forChannels = true)

public suspend fun TelegramBot.getMyDefaultAdministratorRightsForGroupsAndSupergroups(): AdministratorChatMember = getMyDefaultAdministratorRights(forChannels = false)
